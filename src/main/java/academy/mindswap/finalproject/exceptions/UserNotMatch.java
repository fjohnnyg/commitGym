package academy.mindswap.finalproject.exceptions;

public class UserNotMatch extends RuntimeException{
    public UserNotMatch(String message) {
        super("User not match");
    }
}
