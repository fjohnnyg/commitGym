package academy.mindswap.finalproject.exceptions;

public class FitnessTestNotFound extends RuntimeException{

    public FitnessTestNotFound(String message) {
        super("Fitness Test not found");
    }
}
