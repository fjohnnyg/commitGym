package academy.mindswap.finalproject.mapper;

import academy.mindswap.finalproject.dto.DailyPlanDto;
import academy.mindswap.finalproject.model.entities.DailyPlan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DailyPlanMapper {

    DailyPlanDto fromEntityToDailyPlanDto(DailyPlan dailyPlan);

    DailyPlan fromDailyPlanDtoToEntity(DailyPlanDto dailyPlanDto);



}
