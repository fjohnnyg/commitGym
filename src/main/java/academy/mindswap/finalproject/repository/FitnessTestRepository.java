package academy.mindswap.finalproject.repository;

import academy.mindswap.finalproject.model.entities.FitnessTest;
import academy.mindswap.finalproject.model.entities.PersonalTrainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface FitnessTestRepository extends JpaRepository<FitnessTest, Long> {

    @Query(value = "select ft from FitnessTest ft where ft.client.id = :clientId")
    FitnessTest findByUserId(Long clientId);

    @Query(value = "select ft from FitnessTest ft where ft.client.id = :clientId order by ft.date asc limit 1")
    Optional<FitnessTest> findLatestByUserId(Long clientId);


    @Query(value = "SELECT ft FROM FitnessTest ft WHERE ft.client.id = :clientId ORDER BY ft.date ASC")
    List<FitnessTest> findFirst20ByUserId(Long clientId);

    @Query(value = "SELECT ft FROM FitnessTest ft WHERE ft.client.id = :clientId AND ft.date = current_date()")
    Optional<FitnessTest> findCurrentDateFTByClientUsername(Long clientId);

}
