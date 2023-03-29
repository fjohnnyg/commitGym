package academy.mindswap.finalproject.exceptions;

public class WorkoutDoesNotExist extends RuntimeException{
    public WorkoutDoesNotExist(String message) {
        super("Workout doesn't exist");
    }
}
