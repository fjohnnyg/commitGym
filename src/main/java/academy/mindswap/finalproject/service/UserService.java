package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface UserService {



    UserDto getProfile(String username);
    UserDto updateUserProfile(String username, UserDto userDto);
    FitnessTestDto scheduleMyFitnessTest(String username, FitnessTestCreateDto fitnessTestCreateDto);
    void inactivateAccount(String username);
    void addPersonalTrainerAccount(String username, PersonalTrainerUpdateSpecializationDto personalTrainerUpdateSpecializationDto);
    FitnessTestDto getLatestFitnessTest(String username);

    List<FitnessTestDto> getAllFitnessTest(String username);

    DailyPlanDto getDailyPlan(String username, LocalDate date);

    DailyPlanDto getNext7DailyPlan(String username, LocalDate date);
}
