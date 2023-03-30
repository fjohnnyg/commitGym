package academy.mindswap.finalproject.exceptions;

public class DailyPlanNotFoundException extends RuntimeException{

    public DailyPlanNotFoundException() {
        super("Daily-plan not found");
    }

}
