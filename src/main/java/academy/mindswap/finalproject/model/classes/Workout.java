package academy.mindswap.finalproject.model.classes;

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
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime date;
    @OneToMany(mappedBy = "workout", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Set> sets;
}
