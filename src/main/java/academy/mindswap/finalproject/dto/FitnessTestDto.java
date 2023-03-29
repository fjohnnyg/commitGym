package academy.mindswap.finalproject.dto;


import academy.mindswap.finalproject.model.enums.Goal;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FitnessTestDto {

    private String clientUsername;
    private String personalTrainerUsername;
    private LocalDate date;
    private int weight;
    private int height;
    private int bodyFat;
    private int imc;
    private List<Goal> goal;



}
