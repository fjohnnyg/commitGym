package academy.mindswap.finalproject.repository;


import academy.mindswap.finalproject.model.entities.DailyPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;


public interface DailyPlanRepository extends JpaRepository<DailyPlan, Long> {

    @Query(value = "SELECT dp FROM DailyPlan dp WHERE dp.client.id = :userId AND dp.date = :date")
    DailyPlan findByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDate date);

}
