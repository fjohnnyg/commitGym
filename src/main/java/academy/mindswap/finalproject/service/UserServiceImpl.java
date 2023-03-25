package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.FitnessTestCreateDto;
import academy.mindswap.finalproject.dto.FitnessTestDto;
import academy.mindswap.finalproject.dto.UserDto;
import academy.mindswap.finalproject.exceptions.UserNotFoundException;
import academy.mindswap.finalproject.mapper.FitnessTestMapper;
import academy.mindswap.finalproject.mapper.UserMapper;
import academy.mindswap.finalproject.model.entities.FitnessTest;
import academy.mindswap.finalproject.model.entities.User;
import academy.mindswap.finalproject.repository.ClientRepository;
import academy.mindswap.finalproject.repository.FitnessTestRepository;
import academy.mindswap.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final FitnessTestRepository fitnessTestRepository;
    private final ClientRepository clientRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final FitnessTestMapper fitnessTestMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, FitnessTestRepository fitnessTestRepository, ClientRepository clientRepository, UserMapper userMapper, JwtService jwtService, FitnessTestMapper fitnessTestMapper) {
        this.userRepository = userRepository;
        this.fitnessTestRepository = fitnessTestRepository;
        this.clientRepository = clientRepository;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
        this.fitnessTestMapper = fitnessTestMapper;
    }

    @Override
    public UserDto getProfile(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return userMapper.fromUserEntityToUserDto(user);
    }

    @Override
    public UserDto updateUserProfile(String username, UserDto userDto) {
        User user = userRepository.findByUsername(username).orElseThrow();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return userMapper.fromUserEntityToUserDto(user);
    }

    @Override
    public FitnessTestDto scheduleMyFitnessTest(String username, FitnessTestCreateDto fitnessTestCreateDto) {
        //Optional<User> user = userRepository.findByUsername(username);
        FitnessTest userFitnessTest = FitnessTest.builder()
                .date(fitnessTestCreateDto.getDate())
                .imc(0)
                .bodyFat(0)
                .height(0)
                .weight(0)
                .personalTrainer(null)
                .build();
        fitnessTestRepository.save(userFitnessTest);
        return fitnessTestMapper.fromEntityToFitnessTestDto(userFitnessTest);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto setRoleClient(String userEmail) {
        Optional<User> optionalUser = userRepository.findByUsername(userEmail);
        return
        optionalUser.map(userMapper::fromUserEntityToUserDto).orElseThrow(() -> new UserNotFoundException());
    }

}
