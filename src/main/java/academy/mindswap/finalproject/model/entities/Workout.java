package academy.mindswap.finalproject.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int set;
    @Column(nullable = false)
    private int rep;
    @Column(nullable = false)
    private int rest;
    @Column(nullable = false)
    private int load;
    @OneToOne(targetEntity = Exercise.class, fetch = FetchType.EAGER)
    private Exercise exercise;

    @ManyToMany(mappedBy = "workouts", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<DailyPlan> dailyPlans = new ArrayList<>();

}
