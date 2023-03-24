package academy.mindswap.finalproject.model.entities;

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
@Table(name = "daily_plans")
public class DailyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @OneToMany(mappedBy = "dailyPlans", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Client> clients;

    @OneToMany(mappedBy = "dailyPlansPrescribed", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<PersonalTrainer> personalTrainers;

    @ManyToOne(targetEntity = Workout.class, fetch = FetchType.EAGER)
    private List<Workout> workouts;

}
