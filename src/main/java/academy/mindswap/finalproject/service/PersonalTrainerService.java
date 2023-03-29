package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.*;

public interface PersonalTrainerService {

    FitnessTestDto scheduleFitnessTest(FitnessTestCreateDto fitnessTestCreateDto);
    ExerciseDto createExercise(ExerciseDto exerciseDto);
    WorkoutDto createWorkout(WorkoutDto workoutDto);
    DailyPlanDto createDailyPlan(DailyPlanDto dailyPlanDto, String personalTrainerUsername);
    void addClientAccount(String personalTrainerUsername);
    void inactivateAccount(String personalTrainerUsername, UserDeleteAccountDto userDeleteAccountDto);
    FitnessTestDto createFitnessTest(FitnessTestCreateDto fitnessTestCreateDto);
}
