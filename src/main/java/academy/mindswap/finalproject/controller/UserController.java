package academy.mindswap.finalproject.controller;

import academy.mindswap.finalproject.dto.FitnessTestCreateDto;
import academy.mindswap.finalproject.dto.FitnessTestDto;
import academy.mindswap.finalproject.dto.PersonalTrainerUpdateSpecializationDto;
import academy.mindswap.finalproject.dto.UserDto;
import academy.mindswap.finalproject.service.FitnessTestServiceImpl;
import academy.mindswap.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api/v2/user")
public class UserController {

    private UserService userService;
    private FitnessTestServiceImpl fitnessTestService;

    @Autowired
    public UserController(UserService userService, FitnessTestServiceImpl fitnessTestService){
        this.userService = userService;
        this.fitnessTestService = fitnessTestService;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getMyProfile() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        UserDto userProfile = userService.getProfile(username);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
    @PutMapping("/profile-update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        UserDto userProfile = userService.updateUserProfile(username,userDto);
        return new ResponseEntity<>(userProfile, HttpStatus.ACCEPTED);
    }
    @PostMapping("/schedule-fitness-test")
    public ResponseEntity<FitnessTestDto> scheduleFitnessTest(@RequestBody FitnessTestCreateDto fitnessTestCreateDto){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        FitnessTestDto fitnessTestDto = userService.scheduleMyFitnessTest(username, fitnessTestCreateDto);
        return new ResponseEntity<>(fitnessTestDto, HttpStatus.ACCEPTED);
    }
    @PutMapping("/inactive-account")
    public ResponseEntity<Void> inactiveAccount(){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        userService.inactiveAccount(username);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("add-personal-trainer-account")
    public ResponseEntity<Void> addPersonalTrainerAccount(@RequestBody PersonalTrainerUpdateSpecializationDto personalTrainerUpdateSpecializationDto) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        userService.addPersonalTrainerAccount(username, personalTrainerUpdateSpecializationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
