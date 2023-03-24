package academy.mindswap.finalproject.model.entities;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "client")
    private List<FitnessTest> fitnessTests;
    @ManyToOne(targetEntity = DailyPlan.class, fetch = FetchType.EAGER)
    private List<DailyPlan> dailyPlans;
}
