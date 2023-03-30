package academy.mindswap.finalproject.controller;

import academy.mindswap.finalproject.dto.*;
import academy.mindswap.finalproject.service.FitnessTestServiceImpl;
import academy.mindswap.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


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
    @PostMapping("/Client/schedule-fitness-test")
    public ResponseEntity<FitnessTestDto> scheduleFitnessTest(@RequestBody FitnessTestClientRequest fitnessTestClientRequest){
        FitnessTestDto fitnessTestDto = userService.scheduleMyFitnessTest(fitnessTestClientRequest.getPersonalTrainerUsername(), fitnessTestClientRequest.getDate());
        return new ResponseEntity<>(fitnessTestDto, HttpStatus.ACCEPTED);
    }
    @PutMapping("/inactive-account")
    public ResponseEntity<Void> inactiveAccount(){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        userService.inactivateAccount(username);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("add-personal-trainer-account")
    public ResponseEntity<Void> addPersonalTrainerAccount(@RequestBody PersonalTrainerUpdateSpecializationDto personalTrainerUpdateSpecializationDto) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        userService.addPersonalTrainerAccount(username, personalTrainerUpdateSpecializationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/Client/latest-fitness-test")
    public ResponseEntity<FitnessTestDto> getLatestFitnessTest() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        FitnessTestDto latestFitnessTest = userService.getLatestFitnessTest(username);
        return new ResponseEntity<>(latestFitnessTest, HttpStatus.OK);
    }
    @GetMapping("/Client/all-fitness-test")
    public ResponseEntity<List<FitnessTestDto>> getAllFitnessTest() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        List<FitnessTestDto> allFitnessTest = userService.getAllFitnessTest(username);
        return new ResponseEntity<>(allFitnessTest, HttpStatus.OK);
    }
    @GetMapping("/Client/daily-plan")
    public ResponseEntity<DailyPlanDto> getDailyPlan(@RequestParam("date") LocalDate date) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        DailyPlanDto dailyPlanDto = userService.getDailyPlan(username,date);
        return new ResponseEntity<>(dailyPlanDto, HttpStatus.OK);
    }

    @GetMapping("/Client/daily-plan-week")
    public ResponseEntity<DailyPlanDto> getNext7DailyPlan(@RequestParam("date") LocalDate date) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        DailyPlanDto dailyPlanDto = userService.getNext7DailyPlan(username,date);
        return new ResponseEntity<>(dailyPlanDto, HttpStatus.OK);
    }

    @PatchMapping("/Client/daily-plan-done")
    public ResponseEntity<DailyPlanDto> setDailyPlanAsDone(@RequestParam("date") LocalDate date) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        DailyPlanDto dailyPlanDto = userService.setDailyPlanAsDone(username, date);
        return new ResponseEntity<>(dailyPlanDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UserDto> deleteUser() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = user.getUsername();
        userService.deleteUser(userName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
