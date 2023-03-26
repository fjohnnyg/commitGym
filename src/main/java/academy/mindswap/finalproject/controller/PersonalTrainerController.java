package academy.mindswap.finalproject.controller;


import academy.mindswap.finalproject.dto.FitnessTestCreateDto;
import academy.mindswap.finalproject.dto.FitnessTestDto;
import academy.mindswap.finalproject.service.FitnessTestServiceImpl;
import academy.mindswap.finalproject.service.PersonalTrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<FitnessTestDto> scheduleFitnessTest(@RequestBody FitnessTestCreateDto fitnessTestCreateDto){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String personalTrainerUsername = user.getUsername();
        FitnessTestDto fitnessTestDto = personalTrainerService.scheduleFitnessTest(personalTrainerUsername, fitnessTestCreateDto);
        return new ResponseEntity<>(fitnessTestDto, HttpStatus.ACCEPTED);
    }



}
