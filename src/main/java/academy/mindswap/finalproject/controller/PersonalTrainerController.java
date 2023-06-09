package academy.mindswap.finalproject.controller;


import academy.mindswap.finalproject.dto.*;
import academy.mindswap.finalproject.service.FitnessTestServiceImpl;
import academy.mindswap.finalproject.service.PersonalTrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    public ResponseEntity<FitnessTestDto> scheduleFitnessTest(@RequestBody FitnessTestPTRequest fitnessTestPTRequest){
        FitnessTestDto fitnessTestDto = personalTrainerService.scheduleFitnessTest(fitnessTestPTRequest.getClientUsername(), fitnessTestPTRequest.getDate());

        return new ResponseEntity<>(fitnessTestDto, HttpStatus.ACCEPTED);
    }

    @PostMapping("/create-exercise")
    public ResponseEntity<ExerciseDto> createExercise(@RequestBody ExerciseDto exerciseDto) {
        ExerciseDto exercise = personalTrainerService.createExercise(exerciseDto);
        return new ResponseEntity<>(exercise, HttpStatus.ACCEPTED);
    }

    @PostMapping("/create-workout")
    public ResponseEntity<WorkoutDto> createWorkout(@RequestBody WorkoutDto workoutDto) {
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
    @PutMapping("/create-client-account")
    public ResponseEntity<Void> createClientAccount() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String personalTrainerUsername = user.getUsername();
        personalTrainerService.addClientAccount(personalTrainerUsername);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/inactive-account")
    public ResponseEntity<Void> inactiveAccount(@RequestBody UserDeleteAccountDto userDeleteAccountDto) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String personalTrainerUsername = user.getUsername();
        personalTrainerService.inactivateAccount(personalTrainerUsername, userDeleteAccountDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/create-fitness-test")
    public ResponseEntity<FitnessTestDto> createFitnessTest(@RequestBody FitnessTestDto fitnessTestDto) {
        FitnessTestDto fitnessTestCreated = personalTrainerService.createFitnessTest(fitnessTestDto);
        return new ResponseEntity<>(fitnessTestCreated, HttpStatus.ACCEPTED);
    }


}

