package academy.mindswap.finalproject.mapper;

import academy.mindswap.finalproject.dto.WorkoutDto;
import academy.mindswap.finalproject.model.entities.Workout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    Workout fromWorkoutDtoToEntity(WorkoutDto workoutDto);
    WorkoutDto fromEntityToWorkoutDto(Workout workout);

}
