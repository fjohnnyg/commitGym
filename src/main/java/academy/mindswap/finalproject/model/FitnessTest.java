package academy.mindswap.finalproject.model;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(nullable = false)
    private String personalTrainer;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User client;
    @Column(nullable = false)
    private Data date;
    private int weight;
    private int height;
    private int bodyFat;
    private int imc;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Goal goal;

}
