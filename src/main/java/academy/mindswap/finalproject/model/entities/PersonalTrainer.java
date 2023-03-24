package academy.mindswap.finalproject.model.entities;

import academy.mindswap.finalproject.model.enums.Specializations;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personal_trainers")
public class PersonalTrainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column
    private Set<Specializations> specializations;
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "personalTrainer")
    private List<FitnessTest> fitnessTests;
    @ManyToOne(targetEntity = DailyPlan.class, fetch = FetchType.EAGER)
    private List<DailyPlan> dailyPlansPrescribed;

    public PersonalTrainer(User user) {
        this.user = user;
    }
}
