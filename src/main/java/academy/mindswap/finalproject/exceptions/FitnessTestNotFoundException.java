package academy.mindswap.finalproject.exceptions;

public class FitnessTestNotFoundException extends RuntimeException {
    public FitnessTestNotFoundException() {
        super("User not found");
    }
}
