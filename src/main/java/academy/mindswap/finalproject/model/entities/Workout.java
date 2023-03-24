package academy.mindswap.finalproject.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private int set;
    @Column(nullable = false)
    private int rep;
    @Column(nullable = false)
    private int rest;
    @Column(nullable = false)
    private int load;
    @ManyToOne(targetEntity = Exercise.class, fetch = FetchType.EAGER)
    private List<Exercise> exercises;


    @OneToMany(mappedBy = "workouts", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<DailyPlan> DailyPlans;

}
