package academy.mindswap.finalproject.exceptions;

public class PersonalTrainerNotFoundException extends RuntimeException {
    public PersonalTrainerNotFoundException() {
        super("User not found");
    }
}
