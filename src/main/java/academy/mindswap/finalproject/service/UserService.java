package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.FitnessTestCreateDto;
import academy.mindswap.finalproject.dto.FitnessTestDto;
import academy.mindswap.finalproject.dto.PersonalTrainerUpdateSpecializationDto;
import academy.mindswap.finalproject.dto.UserDto;

public interface UserService {



    UserDto getProfile(String username);
    UserDto updateUserProfile(String username, UserDto userDto);
    FitnessTestDto scheduleMyFitnessTest(String username, FitnessTestCreateDto fitnessTestCreateDto);
    void inactiveAccount(String username);
    void addPersonalTrainerAccount(String username, PersonalTrainerUpdateSpecializationDto personalTrainerUpdateSpecializationDto);

}
