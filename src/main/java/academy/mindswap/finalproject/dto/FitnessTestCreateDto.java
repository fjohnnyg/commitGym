package academy.mindswap.finalproject.dto;

import academy.mindswap.finalproject.model.entities.Client;
import academy.mindswap.finalproject.model.entities.PersonalTrainer;
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






}
