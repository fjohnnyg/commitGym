package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.FitnessTestCreateDto;
import academy.mindswap.finalproject.dto.FitnessTestDto;
import academy.mindswap.finalproject.dto.PersonalTrainerDto;
import academy.mindswap.finalproject.mapper.FitnessTestMapper;
import academy.mindswap.finalproject.model.entities.FitnessTest;
import academy.mindswap.finalproject.model.entities.PersonalTrainer;
import academy.mindswap.finalproject.model.entities.User;
import academy.mindswap.finalproject.repository.FitnessTestRepository;
import academy.mindswap.finalproject.repository.PersonalTrainerRepository;
import academy.mindswap.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalTrainerServiceImpl implements PersonalTrainerService {

    UserRepository userRepository;
    PersonalTrainerRepository personalTrainerRepository;
    FitnessTestRepository fitnessTestRepository;

    FitnessTestMapper fitnessTestMapper;

    @Autowired
    public PersonalTrainerServiceImpl(UserRepository userRepository, PersonalTrainerRepository personalTrainerRepository, FitnessTestRepository fitnessTestRepository, FitnessTestMapper fitnessTestMapper) {
        this.userRepository = userRepository;
        this.personalTrainerRepository = personalTrainerRepository;
        this.fitnessTestRepository = fitnessTestRepository;
        this.fitnessTestMapper = fitnessTestMapper;
    }

    @Override
    public FitnessTestDto scheduleFitnessTest(String personalTrainerUsername, FitnessTestCreateDto fitnessTestCreateDto){
        User user = userRepository.findByUsername(fitnessTestCreateDto.getClientUsername()).orElseThrow();
        User userPersonalTrainer = userRepository.findByUsername(personalTrainerUsername).orElseThrow();
        PersonalTrainer personalTrainer = personalTrainerRepository.getReferenceById(userPersonalTrainer.getId());; // findByUsername(personalTrainerUsername).orElseThrow();

        FitnessTest userFitnessTest = FitnessTest.builder()
                .date(fitnessTestCreateDto.getDate())
                .imc(0)
                .bodyFat(0)
                .height(0)
                .weight(0)
                .user(user)
                .personalTrainer(personalTrainer)
                .build();
        fitnessTestRepository.save(userFitnessTest);
        return fitnessTestMapper.fromEntityToFitnessTestDto(userFitnessTest);
    }

}
