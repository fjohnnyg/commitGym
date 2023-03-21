package academy.mindswap.finalproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fitness_test")
public class FitnessTest implements Event{
    @Column(nullable = false)
    private PersonalTrainer personalTrainer;
    @Column(nullable = false)
    private Client client;
    private int weight;
    private int height;
    private int bodyFat;
    private int imc;
    @Column(nullable = false)
    private Date date;
    @Enumerated(EnumType.STRING)
    private Goal goal;

}
