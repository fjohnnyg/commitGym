package academy.mindswap.finalproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private LocalDate birthDate;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String userName;

    /*
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

     */

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<FitnessTest> myFitnessTest = new ArrayList<>();

    @ManyToMany (targetEntity = FitnessTest.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "user_fitnessTests",
    joinColumns = {@JoinColumn(name = "client_id")},
    inverseJoinColumns = {@JoinColumn(name = "fitnessTest_id")})
    private List<FitnessTest> myFitnessTests = new ArrayList<>();
    @ManyToMany (targetEntity = FitnessTest.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "user_fitnessTests",
            joinColumns = {@JoinColumn(name = "personalTrainer_id")},
            inverseJoinColumns = {@JoinColumn(name = "fitnessTest_id")})
    private List<FitnessTest> fitnessTestsDone;

    /*
    @Enumerated(EnumType.STRING)
    @Column
    private Specializations specializations;

     */

}
