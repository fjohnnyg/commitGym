package academy.mindswap.finalproject.controller;

import academy.mindswap.finalproject.auth.AuthenticationRequest;
import academy.mindswap.finalproject.auth.AuthenticationResponse;
import academy.mindswap.finalproject.auth.RegisterRequest;
import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.exceptions.InactiveUser;
import academy.mindswap.finalproject.exceptions.UserNotFoundException;
import academy.mindswap.finalproject.model.entities.User;
import academy.mindswap.finalproject.model.enums.Role;
import academy.mindswap.finalproject.repository.UserRepository;
import academy.mindswap.finalproject.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;


    @PostMapping("/register-client")
    public ResponseEntity<AuthenticationResponse> registerClient(@RequestBody UserCreateDto request) {
        return ResponseEntity.ok(authenticationService.registerClient(request));
    }

    @PostMapping("/register-personal-trainer")
    public ResponseEntity<AuthenticationResponse> registerPersonalTrainer(@RequestBody UserCreateDto request) {
        return ResponseEntity.ok(authenticationService.registerPersonalTrainer(request));
    }

    @PostMapping("/register-admin")
    public ResponseEntity<AuthenticationResponse> adminRegister(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.adminRegister(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(UserNotFoundException::new);
        if(user.getRoles().contains(Role.INACTIVE)){
            throw new InactiveUser("Inactive user");
        }
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
