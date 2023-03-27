package academy.mindswap.finalproject.exceptions;

public class ExerciseAlreadyExist extends RuntimeException{

    public ExerciseAlreadyExist(String message) {
        super("Exercise already exist");
    }
}
