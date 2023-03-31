package academy.mindswap.finalproject.mapper;

import academy.mindswap.finalproject.dto.WorkoutDto;
import academy.mindswap.finalproject.model.entities.Workout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    Workout fromWorkoutDtoToEntity(WorkoutDto workoutDto);
    default WorkoutDto fromEntityToWorkoutDto(Workout workout) {
        if ( workout == null ) {
            return null;
        }

        WorkoutDto.WorkoutDtoBuilder workoutDto = WorkoutDto.builder();

        workoutDto.exerciseName(workout.getExercise().getName());
        workoutDto.set( workout.getSet() );
        workoutDto.rep( workout.getRep() );
        workoutDto.rest( workout.getRest() );
        workoutDto.load( workout.getLoad() );

        return workoutDto.build();
    }

}
