package academy.mindswap.finalproject.repository;

import academy.mindswap.finalproject.model.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository  extends JpaRepository<Exercise, Long> {
}
