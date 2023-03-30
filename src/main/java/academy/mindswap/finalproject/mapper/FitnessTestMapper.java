package academy.mindswap.finalproject.mapper;

import academy.mindswap.finalproject.dto.FitnessTestClientRequest;
import academy.mindswap.finalproject.dto.FitnessTestDto;
import academy.mindswap.finalproject.dto.FitnessTestPTRequest;
import academy.mindswap.finalproject.model.entities.FitnessTest;
import academy.mindswap.finalproject.model.enums.Goal;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FitnessTestMapper {

    default FitnessTestDto fromEntityToFitnessTestDto(FitnessTest fitnessTest){

        if ( fitnessTest == null ) {
            return null;
        }

        FitnessTestDto.FitnessTestDtoBuilder fitnessTestDto = FitnessTestDto.builder();
        fitnessTestDto.clientUsername(fitnessTest.getClient().getUser().getUsername());
        fitnessTestDto.personalTrainerUsername(fitnessTest.getPersonalTrainer().getUser().getUsername());
        fitnessTestDto.date( fitnessTest.getDate() );
        fitnessTestDto.weight( fitnessTest.getWeight() );
        fitnessTestDto.height( fitnessTest.getHeight() );
        fitnessTestDto.bodyFat( fitnessTest.getBodyFat() );
        fitnessTestDto.imc( fitnessTest.getImc() );
        List<Goal> list = fitnessTest.getGoal();
        if ( list != null ) {
            fitnessTestDto.goal( new ArrayList<Goal>( list ) );
        }

        return fitnessTestDto.build();
    }

    List<FitnessTestDto> fromEntityListToFitnessTestDtoList(List<FitnessTest> fitnessTest);



}
