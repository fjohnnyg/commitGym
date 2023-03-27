package academy.mindswap.finalproject.mapper;

import academy.mindswap.finalproject.dto.PersonalTrainerDto;
import academy.mindswap.finalproject.dto.PersonalTrainerUpdateSpecializationDto;
import academy.mindswap.finalproject.model.entities.PersonalTrainer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonalTrainerMapper {

    PersonalTrainer fromPersonalTrainerDtoToEntity(PersonalTrainerDto personalTrainerDto);
    PersonalTrainerDto fromEntityToPersonalTrainerDto(PersonalTrainer personalTrainer);

    PersonalTrainer fromPersonalTrainerUpdateSpecializationDtoToEntity(PersonalTrainerUpdateSpecializationDto personalTrainerUpdateSpecializationDto);
    PersonalTrainerUpdateSpecializationDto fromEntityToPersonalTrainerUpdateSpecializationDto(PersonalTrainer personalTrainer);



}
