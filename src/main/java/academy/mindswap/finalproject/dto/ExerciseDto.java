package academy.mindswap.finalproject.dto;


import academy.mindswap.finalproject.model.enums.Equipment;
import lombok.*;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDto {

    private String name;
    private String description;
    private boolean hasVideo;
    private Equipment equipment;


}
