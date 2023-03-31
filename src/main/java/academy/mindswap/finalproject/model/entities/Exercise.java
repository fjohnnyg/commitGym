package academy.mindswap.finalproject.model.entities;

import academy.mindswap.finalproject.model.enums.Equipment;
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
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column
    private String description;
    @Column
    private boolean hasVideo;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Equipment equipment;

    @OneToOne(mappedBy = "exercise", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Workout workout;

}
