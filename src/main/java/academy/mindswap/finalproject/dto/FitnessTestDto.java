package academy.mindswap.finalproject.dto;

import academy.mindswap.finalproject.model.entities.Client;
import academy.mindswap.finalproject.model.entities.PersonalTrainer;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class FitnessTestDto {

    private Client client;


    private PersonalTrainer personalTrainer;

    @NotBlank(message = "Must have a date")
    private LocalDate date;

    private int weight;

    private int height;

    private int bodyFat;

    private int imc;


}
