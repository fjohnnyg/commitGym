package academy.mindswap.finalproject.controller;


import academy.mindswap.finalproject.dto.*;
import academy.mindswap.finalproject.service.FitnessTestServiceImpl;
import academy.mindswap.finalproject.service.PersonalTrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/personal-trainer")
public class PersonalTrainerController {

    private PersonalTrainerService personalTrainerService;
    private FitnessTestServiceImpl fitnessTestService;

    @Autowired
    public PersonalTrainerController(PersonalTrainerService personalTrainerService, FitnessTestServiceImpl fitnessTestService) {
        this.personalTrainerService = personalTrainerService;
        this.fitnessTestService = fitnessTestService;
    }

    @PostMapping("/schedule-fitness-test")
    public ResponseEntity<FitnessTestDto> scheduleFitnessTest(@RequestBody FitnessTestCreateDto fitnessTestCreateDto){
        FitnessTestDto fitnessTestDto = personalTrainerService.scheduleFitnessTest(fitnessTestCreateDto);
        return new ResponseEntity<>(fitnessTestDto, HttpStatus.ACCEPTED);
    }

    @PostMapping("/create-exercise") //os exercicios não deveriam ser por personal trainer?
    public ResponseEntity<ExerciseDto> createExercise(@RequestBody ExerciseDto exerciseDto) {
        //UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //String personalTrainerUsername = user.getUsername();
        ExerciseDto exercise = personalTrainerService.createExercise(exerciseDto);
        return new ResponseEntity<>(exercise, HttpStatus.ACCEPTED);
    }

    @PostMapping("/create-workout")
    public ResponseEntity<WorkoutDto> createWorkout(@RequestBody WorkoutDto workoutDto) {
        //UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //String personalTrainerUsername = user.getUsername();
        WorkoutDto workout = personalTrainerService.createWorkout(workoutDto);
        return new ResponseEntity<>(workout, HttpStatus.ACCEPTED);
    }

    @PostMapping("/create-client-daily-plan")
    public ResponseEntity<DailyPlanDto> createClientDailyPlan(@RequestBody DailyPlanDto dailyPlanDto) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String personalTrainerUsername = user.getUsername();
        DailyPlanDto clientDailyPlan = personalTrainerService.createDailyPlan(dailyPlanDto, personalTrainerUsername);
        return new ResponseEntity<>(clientDailyPlan, HttpStatus.ACCEPTED);
    }

}

