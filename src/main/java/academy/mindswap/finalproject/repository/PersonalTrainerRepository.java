package academy.mindswap.finalproject.repository;

import academy.mindswap.finalproject.model.entities.Client;
import academy.mindswap.finalproject.model.entities.PersonalTrainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalTrainerRepository extends JpaRepository<PersonalTrainer, Long> {

    @Query(value = "SELECT pt FROM PersonalTrainer pt WHERE pt.user.id = :userId")
    PersonalTrainer findByUserId(Long userId);

    @Query(value = "SELECT pt FROM PersonalTrainer pt WHERE pt.user.username = :username")
    Optional<PersonalTrainer> findByUsername(String username);


}
