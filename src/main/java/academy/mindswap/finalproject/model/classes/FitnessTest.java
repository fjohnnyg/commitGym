package academy.mindswap.finalproject.model.classes;

import academy.mindswap.finalproject.model.enums.Goal;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fitness_tests")
public class FitnessTest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(mappedBy = "myFitnessTests", fetch = FetchType.EAGER)
    private List<User> personalTrainers;
    @ManyToMany(mappedBy = "fitnessTestsDone", fetch = FetchType.EAGER)
    private List<User> client;
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