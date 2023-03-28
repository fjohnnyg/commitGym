package academy.mindswap.finalproject.exceptions;

public class DailyPlanAlreadyExist extends RuntimeException{
    public DailyPlanAlreadyExist(String message) {
        super("This client already have a daily plan for this date");
    }
}
