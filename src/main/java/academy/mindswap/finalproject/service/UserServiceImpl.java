package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.*;
import academy.mindswap.finalproject.exceptions.AlreadyHasPersonalTrainerAccount;
import academy.mindswap.finalproject.exceptions.FitnessTestNotFound;
import academy.mindswap.finalproject.exceptions.UserNotFoundException;
import academy.mindswap.finalproject.mapper.DailyPlanMapper;
import academy.mindswap.finalproject.mapper.FitnessTestMapper;
import academy.mindswap.finalproject.mapper.PersonalTrainerMapper;
import academy.mindswap.finalproject.mapper.UserMapper;
import academy.mindswap.finalproject.model.entities.DailyPlan;
import academy.mindswap.finalproject.model.entities.FitnessTest;
import academy.mindswap.finalproject.model.entities.PersonalTrainer;
import academy.mindswap.finalproject.model.entities.User;
import academy.mindswap.finalproject.model.enums.Role;
import academy.mindswap.finalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final FitnessTestRepository fitnessTestRepository;
    private final PersonalTrainerRepository personalTrainerRepository;
    private final ClientRepository clientRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final FitnessTestMapper fitnessTestMapper;
    private final PasswordEncoder passwordEncoder;
    private final DailyPlanRepository dailyPlanRepository;
    private final DailyPlanMapper dailyPlanMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, FitnessTestRepository fitnessTestRepository, PersonalTrainerRepository personalTrainerRepository, ClientRepository clientRepository, UserMapper userMapper, JwtService jwtService, FitnessTestMapper fitnessTestMapper, PersonalTrainerMapper personalTrainerMapper, PasswordEncoder passwordEncoder, DailyPlanRepository dailyPlanRepository, DailyPlanMapper dailyPlanMapper) {
        this.userRepository = userRepository;
        this.fitnessTestRepository = fitnessTestRepository;
        this.personalTrainerRepository = personalTrainerRepository;
        this.clientRepository = clientRepository;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
        this.fitnessTestMapper = fitnessTestMapper;
        this.passwordEncoder = passwordEncoder;
        this.dailyPlanRepository = dailyPlanRepository;
        this.dailyPlanMapper = dailyPlanMapper;
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
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return userMapper.fromUserEntityToUserDto(user);
    }
    @Override
    public FitnessTestDto scheduleMyFitnessTest(String username, FitnessTestCreateDto fitnessTestCreateDto) {
        User clientUser = userRepository.findByUsername(username).orElseThrow();
        User personalTrainerUser = userRepository.findByUsername(fitnessTestCreateDto.getPersonalTrainerUsername()).orElseThrow();

        PersonalTrainer personalTrainer = personalTrainerRepository.findByUserId(personalTrainerUser.getId());
        FitnessTest userFitnessTest = FitnessTest.builder()
                .date(fitnessTestCreateDto.getDate())
                .imc(0)
                .bodyFat(0)
                .height(0)
                .weight(0)
                .user(clientUser)
                .personalTrainer(personalTrainer)
                .build();
        fitnessTestRepository.save(userFitnessTest);
        return fitnessTestMapper.fromEntityToFitnessTestDto(userFitnessTest);
    }
    @Override
    public void inactiveAccount(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Set<Role> newRole = new HashSet<>();
        newRole.add(Role.INACTIVE);
        user.setRoles(newRole);
        userRepository.save(user);
    }
    @Override
    public void addPersonalTrainerAccount(String username, PersonalTrainerUpdateSpecializationDto personalTrainerUpdateSpecializationDto) {

        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if(user.getRoles().contains(Role.PERSONAL_TRAINER)){
            throw new AlreadyHasPersonalTrainerAccount("The user already have a personal trainer account");
        }

        Set<Role> newRole = new HashSet<>();
        newRole.add(Role.CLIENT);
        newRole.add(Role.PERSONAL_TRAINER);
        user.setRoles(newRole);
        userRepository.save(user);

        PersonalTrainer personalTrainer = new PersonalTrainer(user);
        personalTrainer.setSpecializations(personalTrainerUpdateSpecializationDto.getSpecializations());
        personalTrainerRepository.save(personalTrainer);
    }
    @Override
    public FitnessTestDto getLatestFitnessTest(String username) {

        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if(user.getFitnessTests() == null){
            throw new FitnessTestNotFound("You don't have a fitness test associated to your account");
        }

        FitnessTest fitnessTest = fitnessTestRepository.findLatestByUserId(user.getId()).orElseThrow(UserNotFoundException::new);

        return fitnessTestMapper.fromEntityToFitnessTestDto(fitnessTest);
    }
    @Override
    public List<FitnessTestDto>  getAllFitnessTest(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if(user.getFitnessTests() == null){
            throw new FitnessTestNotFound("You don't have a fitness test associated to your account");
        }

        List<FitnessTest> fitnessTest = fitnessTestRepository.findFirst20ByUserId(user.getId());

        return fitnessTestMapper.fromEntityListToFitnessTestDtoList(fitnessTest);
    }

    @Override
    public DailyPlanDto getDailyPlan(String username, LocalDate date) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        DailyPlan dailyPlan = dailyPlanRepository.findByUserIdAndDate(user.getId(),date);
        return dailyPlanMapper.fromEntityToDailyPlanDto(dailyPlan);
    }


}
