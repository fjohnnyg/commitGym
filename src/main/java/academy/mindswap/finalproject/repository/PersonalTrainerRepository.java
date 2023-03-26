package academy.mindswap.finalproject.repository;

import academy.mindswap.finalproject.model.entities.PersonalTrainer;
import academy.mindswap.finalproject.model.entities.Token;
import academy.mindswap.finalproject.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonalTrainerRepository extends JpaRepository<PersonalTrainer, Long> {



    @Query(value = "select pt from PersonalTrainer pt where pt.user.id = :userId")
    PersonalTrainer findByUserId(Long userId);

    /*
     select t from Token t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)

    List<Token> findUser(Long id);

     */
}
