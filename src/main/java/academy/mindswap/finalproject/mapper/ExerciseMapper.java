package academy.mindswap.finalproject.mapper;

import academy.mindswap.finalproject.dto.ExerciseDto;
import academy.mindswap.finalproject.model.entities.Exercise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    ExerciseDto fromEntityToExerciseDto(Exercise exercise);
    Exercise fromExerciseDtoToEntity(ExerciseDto exerciseDto);

}
