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
    @ManyToMany(mappedBy = "setList", fetch = FetchType.EAGER)
    private List<Exercise> exercises;
    @ManyToOne
    private Workout workout;
}
