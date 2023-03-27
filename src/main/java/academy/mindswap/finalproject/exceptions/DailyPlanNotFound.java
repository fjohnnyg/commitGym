package academy.mindswap.finalproject.exceptions;

public class DailyPlanNotFound extends RuntimeException{

    public DailyPlanNotFound(String message) {
        super("Daily-plan not found");
    }

}
