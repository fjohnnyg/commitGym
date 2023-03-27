package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.*;
import academy.mindswap.finalproject.exceptions.ExerciseAlreadyExist;
import academy.mindswap.finalproject.exceptions.UserNotFoundException;
import academy.mindswap.finalproject.mapper.DailyPlanMapper;
import academy.mindswap.finalproject.mapper.ExerciseMapper;
import academy.mindswap.finalproject.mapper.FitnessTestMapper;
import academy.mindswap.finalproject.mapper.WorkoutMapper;
import academy.mindswap.finalproject.model.entities.*;
import academy.mindswap.finalproject.model.entities.DailyPlan;
import academy.mindswap.finalproject.model.enums.Role;
import academy.mindswap.finalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonalTrainerServiceImpl implements PersonalTrainerService {

    UserRepository userRepository;
    PersonalTrainerRepository personalTrainerRepository;
    FitnessTestRepository fitnessTestRepository;
    FitnessTestMapper fitnessTestMapper;
    ExerciseMapper exerciseMapper;
    ExerciseRepository exerciseRepository;
    WorkoutRepository workoutRepository;
    WorkoutMapper workoutMapper;
    ClientRepository clientRepository;
    DailyPlanRepository dailyPlanRepository;
    DailyPlanMapper dailyPlanMapper;

    @Autowired
    public PersonalTrainerServiceImpl(UserRepository userRepository, PersonalTrainerRepository personalTrainerRepository, FitnessTestRepository fitnessTestRepository, FitnessTestMapper fitnessTestMapper, ExerciseMapper exerciseMapper, ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository, WorkoutMapper workoutMapper, ClientRepository clientRepository, DailyPlanRepository dailyPlanRepository, DailyPlanMapper dailyPlanMapper) {
        this.userRepository = userRepository;
        this.personalTrainerRepository = personalTrainerRepository;
        this.fitnessTestRepository = fitnessTestRepository;
        this.fitnessTestMapper = fitnessTestMapper;
        this.exerciseMapper = exerciseMapper;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
        this.clientRepository = clientRepository;
        this.dailyPlanRepository = dailyPlanRepository;
        this.dailyPlanMapper = dailyPlanMapper;
    }

    @Override
    public FitnessTestDto scheduleFitnessTest(FitnessTestCreateDto fitnessTestCreateDto){

        UserDetails loggedUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String personalTrainerUsername = loggedUser.getUsername();
        User ptuser = userRepository.findByUsername(personalTrainerUsername).orElseThrow();
        PersonalTrainer personalTrainer = personalTrainerRepository.findByUserId(ptuser.getId());

        User user = userRepository.findByUsername(fitnessTestCreateDto.getClientUsername()).orElseThrow(UserNotFoundException::new);

        FitnessTest userFitnessTest = FitnessTest.builder()
                .date(fitnessTestCreateDto.getDate())
                .imc(0)
                .bodyFat(0)
                .height(0)
                .weight(0)
                .user(user)
                .personalTrainer(personalTrainer)
                .build();
        fitnessTestRepository.save(userFitnessTest);
        return fitnessTestMapper.fromEntityToFitnessTestDto(userFitnessTest);
    }

    @Override
    public ExerciseDto createExercise(ExerciseDto exerciseDto) {
        if(exerciseRepository.findByName(exerciseDto.getName()) != null){
            throw new ExerciseAlreadyExist("Exercise already exist");
        }

        Exercise exercise = Exercise.builder()
                .name(exerciseDto.getName())
                .description(exerciseDto.getDescription())
                .equipment(exerciseDto.getEquipment())
                .hasVideo(exerciseDto.isHasVideo())
                .build();
        exerciseRepository.save(exercise);
        return exerciseMapper.fromEntityToExerciseDto(exercise);
    }

    @Override
    public WorkoutDto createWorkout(WorkoutDto workoutDto) {
        Exercise exercise = exerciseRepository.findByName(workoutDto.getExerciseName());
        Workout workout = Workout.builder()
                .set(workoutDto.getSet())
                .rep(workoutDto.getRep())
                .rest(workoutDto.getRest())
                .load(workoutDto.getLoad())
                .exercise(exercise)
                .build();
        workoutRepository.save(workout);
        return workoutMapper.fromEntityToWorkoutDto(workout);
    }

    @Override
    public DailyPlanDto createDailyPlan(DailyPlanDto dailyPlanDto, String personalTrainerUsername) {
        String clientUsername = dailyPlanDto.getClientUsername();
        User userClient = userRepository.findByUsername(clientUsername).orElseThrow(UserNotFoundException::new);
        Client client = clientRepository.findByUserId(userClient.getId());

        User userPersonalTrainer = userRepository.findByUsername(personalTrainerUsername).orElseThrow(UserNotFoundException::new);
        PersonalTrainer personalTrainer = userPersonalTrainer.getPersonalTrainer();


        List<Workout> workouts = new ArrayList<>();
        for (Long workoutId : dailyPlanDto.getWorkoutsId()) {
            Workout workout = workoutRepository.getReferenceById(workoutId);
            workouts.add(workout);
        }

        DailyPlan dailyPlan = DailyPlan.builder()
                .date(dailyPlanDto.getDate())
                .client(client)
                .personalTrainers(personalTrainer)
                .workouts(workouts)
                .build();

        dailyPlanRepository.save(dailyPlan);
        return dailyPlanMapper.fromEntityToDailyPlanDto(dailyPlan);
    }

    @Override
    public void createClientAccount(String personalTrainerUsername) {
        User user = userRepository.findByUsername(personalTrainerUsername).orElseThrow(UserNotFoundException::new);
        Set<Role> newRole = new HashSet<>();
        newRole.add(Role.CLIENT);
        newRole.add(Role.PERSONAL_TRAINER);
        user.setRoles(newRole);
        userRepository.save(user);

        Client client = new Client(user);
        clientRepository.save(client);

    }

    @Override
    public void inactiveAccount(String personalTrainerUsername, UserDeleteAccountDto userDeleteAccountDto) {
        User user = userRepository.findByUsername(personalTrainerUsername).orElseThrow(UserNotFoundException::new);
        if(userDeleteAccountDto.getProfileToInactive() == "PERSONAL TRAINER"){
            Set<Role> newRole = new HashSet<>();
            newRole.add(Role.CLIENT);
            user.setRoles(newRole);
            userRepository.save(user);
            return;
        }
        if(userDeleteAccountDto.getProfileToInactive() == "CLIENT"){
            Set<Role> newRole = new HashSet<>();
            newRole.add(Role.PERSONAL_TRAINER);
            user.setRoles(newRole);
            userRepository.save(user);
            return;
        }

    }


}