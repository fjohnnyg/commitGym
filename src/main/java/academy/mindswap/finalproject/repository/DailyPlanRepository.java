package academy.mindswap.finalproject.repository;


import academy.mindswap.finalproject.model.entities.DailyPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;


public interface DailyPlanRepository extends JpaRepository<DailyPlan, Long> {

    @Query(value = "select dp from DailyPlan dp where dp.client.id = :clientId and dp.date = :date")
    Optional<DailyPlan> findByUserIdAndDate(Long clientId, @Param("date") LocalDate date);

    @Query(value = "select dp from DailyPlan dp where dp.client.id = :clientId and dp.date = :date ORDER BY dp.date ASC")
    Optional<DailyPlan> findFirst7ByUserIdAndDate(Long clientId, @Param("date") LocalDate date);

}
