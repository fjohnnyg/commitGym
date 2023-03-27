package academy.mindswap.finalproject.dto;



import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyPlanDto {

    private LocalDate date;
    private String clientUsername;
    private List<Long> workoutsId;


}
