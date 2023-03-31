package academy.mindswap.finalproject.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "daily_plans")
public class DailyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
   private LocalDate date;

    @Column
    private Boolean done;

    @ManyToOne(targetEntity = Client.class, fetch = FetchType.EAGER)
    private Client client;


    @ManyToOne(targetEntity = PersonalTrainer.class, fetch = FetchType.EAGER)
    private PersonalTrainer personalTrainers;


    @ManyToMany(targetEntity = Workout.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "dailyPlans_workouts",
            joinColumns = {@JoinColumn(name = "workout_id")},
            inverseJoinColumns = {@JoinColumn(name = "daily_plans_id")})
    private List<Workout> workouts = new ArrayList<>();


}
