package academy.mindswap.finalproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private List<Client> clients;
    private List<PersonalTrainer> personalTrainers;
}
