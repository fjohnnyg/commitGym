package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.auth.AuthenticationRequest;
import academy.mindswap.finalproject.auth.AuthenticationResponse;
import academy.mindswap.finalproject.auth.RegisterRequest;
import academy.mindswap.finalproject.dto.PersonalTrainerCreateDto;
import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.model.entities.Client;
import academy.mindswap.finalproject.model.entities.PersonalTrainer;
import academy.mindswap.finalproject.model.entities.Token;
import academy.mindswap.finalproject.model.entities.User;
import academy.mindswap.finalproject.model.enums.Role;
import academy.mindswap.finalproject.model.enums.TokenType;
import academy.mindswap.finalproject.repository.ClientRepository;
import academy.mindswap.finalproject.repository.PersonalTrainerRepository;
import academy.mindswap.finalproject.repository.TokenRepository;
import academy.mindswap.finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final ClientRepository clientRepository;

    private final PersonalTrainerRepository personalTrainerRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerClient(UserCreateDto request) {
        User clientUser = registerUser(request);
        clientUser.setRoles(new HashSet<>(Arrays.asList(Role.CLIENT)));
        userRepository.save(clientUser);
        String jwtToken = jwtService.generateToken(clientUser);
        saveUserToken(clientUser, jwtToken);
        Client client = new Client(clientUser);
        clientRepository.save(client);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse registerPersonalTrainer(UserCreateDto request) {
        User personalTrainerUser = registerUser(request);
        personalTrainerUser.setRoles(new HashSet<>(Arrays.asList(Role.PERSONAL_TRAINER)));
        userRepository.save(personalTrainerUser);
        String jwtToken = jwtService.generateToken(personalTrainerUser);
        saveUserToken(personalTrainerUser, jwtToken);
        PersonalTrainer personalTrainer = new PersonalTrainer(personalTrainerUser);
        personalTrainerRepository.save(personalTrainer);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public User registerUser(UserCreateDto request){
        Set<Role> role = new HashSet<>(Arrays.asList(Role.GENERIC));

        User createdUser = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .birthDate(request.getBirthDate())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(role)
                .build();
        return createdUser;
    }

    public AuthenticationResponse adminRegister(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}