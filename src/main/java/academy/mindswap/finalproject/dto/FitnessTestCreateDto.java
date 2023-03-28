package academy.mindswap.finalproject.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FitnessTestCreateDto {

    @NotBlank(message = "Must have a date")
    private LocalDate date;
    private String clientUsername;
    private String personalTrainerUsername;
    private int weight;
    private int height;
    private int bodyFat;
    private int imc;






}
