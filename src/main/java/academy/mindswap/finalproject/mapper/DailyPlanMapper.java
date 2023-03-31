package academy.mindswap.finalproject.mapper;

import academy.mindswap.finalproject.dto.DailyPlanDto;
import academy.mindswap.finalproject.model.entities.DailyPlan;
import academy.mindswap.finalproject.model.entities.Workout;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DailyPlanMapper {

    default DailyPlanDto fromEntityToDailyPlanDto(DailyPlan dailyPlan) {
        if ( dailyPlan == null ) {
            return null;
        }

        DailyPlanDto.DailyPlanDtoBuilder dailyPlanDto = DailyPlanDto.builder();

        dailyPlanDto.clientUsername(dailyPlan.getClient().getUser().getUsername());
        dailyPlanDto.date( dailyPlan.getDate() );
        dailyPlanDto.done( dailyPlan.getDone() );
        dailyPlanDto.workoutsId(dailyPlan.getWorkouts().stream().map(workout -> workout.getId()).toList());


        return dailyPlanDto.build();
    }

    DailyPlan fromDailyPlanDtoToEntity(DailyPlanDto dailyPlanDto);



}
