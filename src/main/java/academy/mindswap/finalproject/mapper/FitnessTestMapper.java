package academy.mindswap.finalproject.mapper;

import academy.mindswap.finalproject.dto.FitnessTestCreateDto;
import academy.mindswap.finalproject.dto.FitnessTestDto;
import academy.mindswap.finalproject.model.entities.FitnessTest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FitnessTestMapper {

    FitnessTest fromFitnessTestCreateDtoScheduleToEntity(FitnessTestCreateDto fitnessTestCreateDto);

    FitnessTestCreateDto fromEntityToFitnessTestCreateDtoSchedule(FitnessTest fitnessTest);

    FitnessTestDto fromEntityToFitnessTestDto(FitnessTest fitnessTest);



}
