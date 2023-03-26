package academy.mindswap.finalproject.repository;

import academy.mindswap.finalproject.model.entities.Client;
import academy.mindswap.finalproject.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
   // Client findByUserid(User user);

}
