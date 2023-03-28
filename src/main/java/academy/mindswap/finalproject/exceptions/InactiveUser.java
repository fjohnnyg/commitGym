package academy.mindswap.finalproject.exceptions;

public class InactiveUser extends RuntimeException{

    public InactiveUser(String message) {
        super("Inactive user");
    }
}
