package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.*;
import academy.mindswap.finalproject.exceptions.*;
import academy.mindswap.finalproject.mapper.*;
import academy.mindswap.finalproject.model.entities.*;
import academy.mindswap.finalproject.model.enums.Role;
import academy.mindswap.finalproject.model.enums.Specializations;
import academy.mindswap.finalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Cacheable(value = "itemCache")
    public UserDto getProfile(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return userMapper.fromUserEntityToUserDto(user);
    }
    @Override
    public UserDto updateUserProfile(String username, UserDto userDto) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return userMapper.fromUserEntityToUserDto(user);
    }
    @Override
    public FitnessTestDto scheduleMyFitnessTest(String personalTrainerUsername, LocalDate date) {
        UserDetails loggedUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String clientUsername = loggedUser.getUsername();
        Client client = clientRepository.findByUsername(clientUsername).orElseThrow(UserNotFoundException::new);
        PersonalTrainer personalTrainer = personalTrainerRepository.findByUsername(personalTrainerUsername).orElseThrow(UserNotFoundException::new);/*(fitnessTestCreateDto.getPersonalTrainerUsername())*/

        if (date.isBefore(LocalDate.now())){
            throw new InvalidDate("Invalid date");
        }

        FitnessTest clientFitnessTest = FitnessTest.builder()
                .date(date)
                .imc(0)
                .bodyFat(0)
                .height(0)
                .weight(0)
                .client(client)
                .personalTrainer(personalTrainer)
                .build();
        fitnessTestRepository.save(clientFitnessTest);
        return fitnessTestMapper.fromEntityToFitnessTestDto(clientFitnessTest);
    }
    @Override
    public void inactivateAccount(String username) {
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

        for (Specializations specializations : personalTrainerUpdateSpecializationDto.getSpecializations()) {
            String specialization = String.valueOf(specializations);
            try {
                Specializations specializationCheck = Specializations.valueOf(specialization);
            } catch (SpecializationDoesNotExist ex) {
            }
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

        Client client = clientRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if(client.getFitnessTests() == null){
            throw new FitnessTestNotFound("You don't have a fitness test associated to your account");
        }

        FitnessTest fitnessTest = fitnessTestRepository.findLatestByUserId(client.getId()).orElseThrow(UserNotFoundException::new);

        return fitnessTestMapper.fromEntityToFitnessTestDto(fitnessTest);
    }
    @Override
    public List<FitnessTestDto>  getAllFitnessTest(String username) {

        Client client = clientRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if(client.getFitnessTests() == null){
            throw new FitnessTestNotFound("You don't have a fitness test associated to your account");
        }

        List<FitnessTest> fitnessTest = fitnessTestRepository.findFirst20ByUserId(client.getId());

        return fitnessTestMapper.fromEntityListToFitnessTestDtoList(fitnessTest);
    }
    @Override
    public DailyPlanDto getDailyPlan(String username, LocalDate date) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Client client = clientRepository.findByUserId(user.getId());
        DailyPlan dailyPlan = dailyPlanRepository.findByUserIdAndDate(client.getId(),date).orElseThrow(UserNotFoundException::new);
        return dailyPlanMapper.fromEntityToDailyPlanDto(dailyPlan);
    }
    @Override
    public DailyPlanDto getNext7DailyPlan(String username, LocalDate date) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Client client = clientRepository.findByUserId(user.getId());
        DailyPlan dailyPlan = dailyPlanRepository.findFirst7ByUserIdAndDate(client.getId(),date).orElseThrow(UserNotFoundException::new);
        return dailyPlanMapper.fromEntityToDailyPlanDto(dailyPlan);
    }

    @Override
    public DailyPlanDto setDailyPlanAsDone(String username, LocalDate date) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Client client = clientRepository.findByUserId(user.getId());
        DailyPlan dailyPlan = dailyPlanRepository.findByUserIdAndDate(client.getId(),date).orElseThrow(UserNotFoundException::new);
        dailyPlan.setDone(true);
        dailyPlanRepository.save(dailyPlan);
        return dailyPlanMapper.fromEntityToDailyPlanDto(dailyPlan);
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Long userId = user.getId();
        userRepository.deleteById(userId);
    }
}
