package academy.mindswap.finalproject.dto;

import academy.mindswap.finalproject.model.entities.Workout;
import academy.mindswap.finalproject.model.enums.Equipment;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
