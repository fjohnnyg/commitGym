package academy.mindswap.finalproject.model.entities;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;

    /*
    @OneToMany(mappedBy = "user")
    private List<FitnessTest> fitnessTests;

     */
    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<DailyPlan> dailyPlans;

    public Client(User user) {
        this.user = user;
    }


}
