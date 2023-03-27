package academy.mindswap.finalproject.repository;

import academy.mindswap.finalproject.model.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository <Workout, Long> {
}
