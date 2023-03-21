package academy.mindswap.finalproject.model;

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
@Table(name = "workout")
public class Workout {

    private List<Exercise> exercises;
    private int duration;
    private WorkoutType workoutType;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
