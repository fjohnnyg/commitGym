package academy.mindswap.finalproject.exceptions;

public class DailyPlanNotFoundException extends RuntimeException{

    public DailyPlanNotFoundException(String message) {
        super("Daily-plan not found");
    }

}
