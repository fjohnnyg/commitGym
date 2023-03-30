package academy.mindswap.finalproject.repository;

import academy.mindswap.finalproject.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT c FROM Client c WHERE c.user.id = :userId")
    Client findByUserId(Long userId);

    @Query(value = "SELECT c FROM Client c WHERE c.user.username = :username")
    Optional<Client> findByUsername(String username);
}



