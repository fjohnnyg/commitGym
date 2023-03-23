package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.dto.FitnessTestCreateDtoBySchedule;
import academy.mindswap.finalproject.mapper.FitnessTestMapper;
import academy.mindswap.finalproject.model.classes.FitnessTest;
import academy.mindswap.finalproject.repository.FitnessTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FitnessTestServiceImpl implements FitnessTestService {

    private FitnessTestMapper fitnessTestMapper;
    private FitnessTestRepository fitnessTestRepository;

    @Autowired
    public FitnessTestServiceImpl(FitnessTestMapper fitnessTestMapper, FitnessTestRepository fitnessTestRepository) {
        this.fitnessTestRepository= fitnessTestRepository;
        this.fitnessTestMapper = fitnessTestMapper;
    }


    public void schedule(Long id, FitnessTestCreateDtoBySchedule fitnessTestCreateDtoBySchedule) {
        FitnessTest fitnessTest = fitnessTestMapper.fromFitnessTestCreateDtoScheduleToEntity(fitnessTestCreateDtoBySchedule);
        fitnessTestRepository.save(fitnessTest);
    }



}
