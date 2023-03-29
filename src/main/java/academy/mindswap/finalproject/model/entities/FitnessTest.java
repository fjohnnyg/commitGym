package academy.mindswap.finalproject.model.entities;

import academy.mindswap.finalproject.model.enums.Goal;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fitness_tests")
public class FitnessTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "personal_trainer_id")
    private PersonalTrainer personalTrainer;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = true)
    private int weight;

    @Column(nullable = true)
    private int height;

    @Column(nullable = true)
    private int bodyFat;

    @Column(nullable = true)
    private int imc;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private List<Goal> goal;


}