package academy.mindswap.finalproject.dto;


import lombok.*;

import java.time.LocalDate;

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


}
