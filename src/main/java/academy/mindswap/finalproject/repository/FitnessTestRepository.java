package academy.mindswap.finalproject.repository;

import academy.mindswap.finalproject.model.FitnessTest;
import academy.mindswap.finalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FitnessTestRepository extends JpaRepository<FitnessTest, Long> {
}
