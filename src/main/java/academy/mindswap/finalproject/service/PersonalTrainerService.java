package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.*;
import academy.mindswap.finalproject.model.entities.Client;
import academy.mindswap.finalproject.model.entities.FitnessTest;

import java.time.LocalDate;

public interface PersonalTrainerService {

    FitnessTestDto scheduleFitnessTest(String clientUsername, LocalDate date);
    ExerciseDto createExercise(ExerciseDto exerciseDto);
    WorkoutDto createWorkout(WorkoutDto workoutDto);
    DailyPlanDto createDailyPlan(DailyPlanDto dailyPlanDto, String personalTrainerUsername);
    void addClientAccount(String personalTrainerUsername);
    void inactivateAccount(String personalTrainerUsername, UserDeleteAccountDto userDeleteAccountDto);
    FitnessTestDto createFitnessTest(FitnessTestDto fitnessTestDto);
}
