package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.*;
import academy.mindswap.finalproject.exceptions.*;
import academy.mindswap.finalproject.mapper.*;
import academy.mindswap.finalproject.model.entities.*;
import academy.mindswap.finalproject.model.enums.Role;
import academy.mindswap.finalproject.model.enums.Specializations;
import academy.mindswap.finalproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
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
    GoogleCalendarService googleCalendarService;

    @Autowired
    public PersonalTrainerServiceImpl(UserRepository userRepository, PersonalTrainerRepository personalTrainerRepository, FitnessTestRepository fitnessTestRepository, FitnessTestMapper fitnessTestMapper, ExerciseMapper exerciseMapper, ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository, WorkoutMapper workoutMapper, ClientRepository clientRepository, DailyPlanRepository dailyPlanRepository, DailyPlanMapper dailyPlanMapper, GoogleCalendarService googleCalendarService) {
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
        this.googleCalendarService = googleCalendarService;
    }
    @Override
    public FitnessTestDto scheduleFitnessTest(String clientUsername, LocalDate date){

        UserDetails loggedUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String personalTrainerUsername = loggedUser.getUsername();
        PersonalTrainer personalTrainer = personalTrainerRepository.findByUsername(personalTrainerUsername).orElseThrow(UserNotFoundException::new);

        Client client = clientRepository.findByUsername(clientUsername).orElseThrow(UserNotFoundException::new);

        if (date.isBefore(LocalDate.now())){
            throw new InvalidDate("Invalid date");
        }

        FitnessTest userFitnessTest = FitnessTest.builder()
                .date(date)
                .imc(0)
                .bodyFat(0)
                .height(0)
                .weight(0)
                .client(client)
                .personalTrainer(personalTrainer)
                .build();
        fitnessTestRepository.save(userFitnessTest);

        try {
            googleCalendarService.addEventToMyCalendar();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fitnessTestMapper.fromEntityToFitnessTestDto(userFitnessTest);
    }
    @Override
    public FitnessTestDto createFitnessTest(FitnessTestDto fitnessTestDto){

        UserDetails loggedUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String personalTrainerUsername = loggedUser.getUsername();
        PersonalTrainer personalTrainer = personalTrainerRepository.findByUsername(personalTrainerUsername).orElseThrow(UserNotFoundException::new);

        String clientUsername = fitnessTestDto.getClientUsername();
        Client client = clientRepository.findByUsername(clientUsername).orElseThrow(UserNotFoundException::new);
        Long clientId = client.getId();

        FitnessTest fitnessTest = fitnessTestRepository.findCurrentDateFTByClientUsername(clientId).orElse(new FitnessTest());



        //fitnessTestRepository.findLatestByUserId(clientUser.getId()).get().getDate().compareTo(fitnessTestCreateDto.getDate());

/*
        Boolean notScheduleFitnessTest = fitnessTestRepository.findLatestByUserId(clientUser.getId()).isEmpty();

        if(!notScheduleFitnessTest && notScheduleFitnessTest.getWeight() != 0 && notScheduleFitnessTest.getDate() == fitnessTestCreateDto.getDate()){
            throw new FitnessTestAlreadyDone("The fitness test schedule for this date is already done, schedule another one");
        }

        Boolean alreadySchedule = false;
        if(!notScheduleFitnessTest) {

        }
            && notScheduleFitnessTest.getWeight() == 0){
            alreadySchedule = true;
        }



        FitnessTest userFitnessTest = null;

        if(alreadySchedule || notScheduleFitnessTest) {

 */

        fitnessTest = FitnessTest.builder()
                    .date(fitnessTestDto.getDate())
                    .imc(fitnessTestDto.getImc())
                    .bodyFat(fitnessTestDto.getBodyFat())
                    .height(fitnessTestDto.getHeight())
                    .weight(fitnessTestDto.getWeight())
                    .goal(fitnessTestDto.getGoal())
                    .client(client)
                    .personalTrainer(personalTrainer)
                    .build();

            fitnessTestRepository.save(fitnessTest);
        return fitnessTestMapper.fromEntityToFitnessTestDto(fitnessTest);
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

        if(client.getDailyPlans().contains(dailyPlanDto.getDate())){
            throw new DailyPlanAlreadyExist("This user already have a daily plan on this date");
        }

        User userPersonalTrainer = userRepository.findByUsername(personalTrainerUsername).orElseThrow(UserNotFoundException::new);
        PersonalTrainer personalTrainer = userPersonalTrainer.getPersonalTrainer();


        List<Workout> workouts = new ArrayList<>();
        for (Long workoutId : dailyPlanDto.getWorkoutsId()) {
            try {
                Workout workout = workoutRepository.getReferenceById(workoutId);
                workouts.add(workout);
            } catch (WorkoutDoesNotExist ex) {
            }
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
    public void addClientAccount(String personalTrainerUsername) {
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
    public void inactivateAccount(String personalTrainerUsername, UserDeleteAccountDto userDeleteAccountDto) {
        User user = userRepository.findByUsername(personalTrainerUsername).orElseThrow(UserNotFoundException::new);

        String roleName = userDeleteAccountDto.getProfileToInactive();
        try {
            Role.valueOf(roleName);
        } catch (SpecializationDoesNotExist ex) {
        }

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
        }
    }


}
