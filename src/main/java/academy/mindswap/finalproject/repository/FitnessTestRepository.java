package academy.mindswap.finalproject.repository;

import academy.mindswap.finalproject.model.entities.FitnessTest;
import academy.mindswap.finalproject.model.entities.PersonalTrainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface FitnessTestRepository extends JpaRepository<FitnessTest, Long> {

    @Query(value = "select ft from FitnessTest ft where ft.user.id = :userId")
    FitnessTest findByUserId(Long userId);

    @Query(value = "select ft from FitnessTest ft where ft.user.id = :userId order by ft.date asc limit 1")
    Optional<FitnessTest> findLatestByUserId(Long userId);

    @Query(value = "select ft from FitnessTest ft where ft.user.id = :userId order by ft.date asc limit 10")
    FitnessTest findAllByUserId(Long userId);

    @Query(value = "SELECT ft FROM FitnessTest ft WHERE ft.user.id = :userId ORDER BY ft.date ASC")
    List<FitnessTest> findFirst20ByUserId(Long userId);

}
