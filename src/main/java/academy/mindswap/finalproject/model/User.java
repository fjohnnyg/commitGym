package academy.mindswap.finalproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private Date birthDate;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String userName;
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
    @Column(nullable = false)
    private String password;
    @OneToMany (mappedBy = "client", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<FitnessTest> fitnessTestList;
    @Enumerated(EnumType.STRING)
    private List<Specializations> specializations;

}
