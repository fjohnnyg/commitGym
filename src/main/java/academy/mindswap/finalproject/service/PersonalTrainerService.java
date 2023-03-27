package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.*;
import academy.mindswap.finalproject.model.entities.DailyPlan;

public interface PersonalTrainerService {

    FitnessTestDto scheduleFitnessTest(FitnessTestCreateDto fitnessTestCreateDto);


    ExerciseDto createExercise(ExerciseDto exerciseDto);

    WorkoutDto createWorkout(WorkoutDto workoutDto);

    DailyPlanDto createDailyPlan(DailyPlanDto dailyPlanDto, String personalTrainerUsername);
}
