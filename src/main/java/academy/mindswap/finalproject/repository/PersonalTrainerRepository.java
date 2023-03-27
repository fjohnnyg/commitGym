package academy.mindswap.finalproject.repository;

import academy.mindswap.finalproject.model.entities.PersonalTrainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalTrainerRepository extends JpaRepository<PersonalTrainer, Long> {

    @Query(value = "select pt from PersonalTrainer pt where pt.user.id = :userId")
    PersonalTrainer findByUserId(Long userId);


}
