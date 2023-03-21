package academy.mindswap.finalproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fitness_test")
public class FitnessTest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User personalTrainer;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User client;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column
    private int weight;
    @Column
    private int height;
    @Column
    private int bodyFat;
    @Column
    private int imc;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Goal goal;

}