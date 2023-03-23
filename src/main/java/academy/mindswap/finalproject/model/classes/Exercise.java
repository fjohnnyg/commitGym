package academy.mindswap.finalproject.model.classes;

import academy.mindswap.finalproject.model.enums.Equipment;
import jakarta.persistence.*;
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
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column
    private boolean hasVideo;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Equipment equipment;

    @OneToMany(mappedBy = "exercises", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Set> sets;


/*
    @ManyToMany (targetEntity = Set.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "exercise_sets",
            joinColumns = {@JoinColumn(name = "exercise_id")},
            inverseJoinColumns = {@JoinColumn(name = "set_id")})

    private List<Set> setList = new ArrayList<>();

 */

}
