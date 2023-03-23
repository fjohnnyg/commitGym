package academy.mindswap.finalproject.mapper;

import academy.mindswap.finalproject.dto.FitnessTestCreateDtoBySchedule;
import academy.mindswap.finalproject.model.classes.FitnessTest;
import academy.mindswap.finalproject.model.enums.Goal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FitnessTestMapper {

    FitnessTest fromFitnessTestCreateDtoScheduleToEntity(FitnessTestCreateDtoBySchedule fitnessTestCreateDtoBySchedule);

    /*
    default FitnessTest fromFitnessTestCreateDtoScheduleToEntity(FitnessTestCreateDtoBySchedule fitnessTestCreateDtoBySchedule){

        FitnessTest.FitnessTestBuilder fitnessTest = FitnessTest.builder();

        fitnessTest.date((fitnessTestCreateDtoBySchedule.getDate()));
        fitnessTest.weight(0);
        fitnessTest.height(0);
        fitnessTest.imc(0);
        fitnessTest.goal(Goal.GENERIC);

        return fitnessTest.build();

    };

     */

}
