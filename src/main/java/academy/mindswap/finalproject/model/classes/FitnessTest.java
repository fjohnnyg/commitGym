package academy.mindswap.finalproject.model.classes;

import academy.mindswap.finalproject.model.enums.Goal;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    @ManyToMany(mappedBy = "myFitnessTests", fetch = FetchType.EAGER)
    private List<User> personalTrainers;
    @ManyToMany(mappedBy = "fitnessTestsDone", fetch = FetchType.EAGER)
    private List<User> client;
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

    /*
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Goal goal;

     */
}