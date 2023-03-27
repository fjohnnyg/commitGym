package academy.mindswap.finalproject.repository;

import academy.mindswap.finalproject.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.user.id = :userId")
    Client findByUserId(Long userId);

    /*
    @Query(value = "select c from Clients c where c.user.id = :userId")
    Client findByUserId(Long userId);

     */

}

 /*
    @Query(value = "select pt from PersonalTrainer pt where pt.user.id = :userId")
    PersonalTrainer findByUserId(Long userId);

     select t from Token t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)

  */

