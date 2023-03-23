package academy.mindswap.finalproject.controller;

import academy.mindswap.finalproject.dto.FitnessTestCreateDtoBySchedule;
import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.dto.UserDto;
import academy.mindswap.finalproject.service.FitnessTestServiceImpl;
import academy.mindswap.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private FitnessTestServiceImpl fitnessTestService;

    @Autowired
    public UserController(UserService userService, FitnessTestServiceImpl fitnessTestService){
        this.userService = userService;
        this.fitnessTestService = fitnessTestService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreateDto user) {
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        UserDto updatedUser = userService.updateUser(id, userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @PostMapping("/{id}/schedule-fitness-test")
    public ResponseEntity<Void> scheduleFitnessTest(@PathVariable Long id, @RequestBody FitnessTestCreateDtoBySchedule fitnessTestCreateDtoBySchedule){
        fitnessTestService.schedule(id, fitnessTestCreateDtoBySchedule);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }



}
