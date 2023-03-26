package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.ExerciseDto;
import academy.mindswap.finalproject.dto.FitnessTestCreateDto;
import academy.mindswap.finalproject.dto.FitnessTestDto;

public interface PersonalTrainerService {

    FitnessTestDto scheduleFitnessTest(String personalTrainerUsername, FitnessTestCreateDto fitnessTestCreateDto);


    ExerciseDto createExercise(ExerciseDto exerciseDto);
}
