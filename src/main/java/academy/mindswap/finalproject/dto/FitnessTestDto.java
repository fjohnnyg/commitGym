package academy.mindswap.finalproject.dto;

import academy.mindswap.finalproject.model.entities.Client;
import academy.mindswap.finalproject.model.entities.PersonalTrainer;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
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
