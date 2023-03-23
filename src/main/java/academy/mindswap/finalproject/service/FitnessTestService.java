package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.FitnessTestCreateDtoBySchedule;
import academy.mindswap.finalproject.mapper.FitnessTestMapper;
import academy.mindswap.finalproject.mapper.UserMapper;
import academy.mindswap.finalproject.model.FitnessTest;
import academy.mindswap.finalproject.model.User;
import academy.mindswap.finalproject.repository.FitnessTestRepository;
import academy.mindswap.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FitnessTestService {

    private FitnessTestMapper fitnessTestMapper;
    private FitnessTestRepository fitnessTestRepository;

    @Autowired
    public FitnessTestService(FitnessTestMapper fitnessTestMapper, FitnessTestRepository fitnessTestRepository) {
        this.fitnessTestRepository= fitnessTestRepository;
        this.fitnessTestMapper = fitnessTestMapper;
    }


    public void schedule(Long id, FitnessTestCreateDtoBySchedule fitnessTestCreateDtoBySchedule) {
        FitnessTest fitnessTest = fitnessTestMapper.fromFitnessTestCreateDtoScheduleToEntity(fitnessTestCreateDtoBySchedule);
        fitnessTestRepository.save(fitnessTest);
    }



}
