package academy.mindswap.finalproject.exceptions;

public class ExerciseNotFound extends RuntimeException{

    public ExerciseNotFound(String message) {
        super("Exercise not found");
    }
}
