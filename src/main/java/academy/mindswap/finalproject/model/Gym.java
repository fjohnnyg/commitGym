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
@Table(name = "gym")
public class Gym {

    private List<Event> events;
    private List<User> userClient;
    private List<User> userPersonalTrainers;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
