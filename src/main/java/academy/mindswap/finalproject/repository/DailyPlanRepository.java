package academy.mindswap.finalproject.repository;

import academy.mindswap.finalproject.model.entities.DailyPlan;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DailyPlanRepository extends JpaRepository<DailyPlan, Long> {
}
