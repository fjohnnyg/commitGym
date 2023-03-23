package academy.mindswap.finalproject.model.classes;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sets")
public class Set {
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


    @OneToMany(mappedBy = "sets", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Workout> workouts;

}
