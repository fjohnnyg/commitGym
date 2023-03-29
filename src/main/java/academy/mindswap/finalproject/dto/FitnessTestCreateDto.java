package academy.mindswap.finalproject.dto;


import academy.mindswap.finalproject.model.enums.Goal;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

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
    private List<Goal> goal;






}
