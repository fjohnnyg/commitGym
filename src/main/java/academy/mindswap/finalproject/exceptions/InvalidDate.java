package academy.mindswap.finalproject.exceptions;

public class InvalidDate extends RuntimeException{
    public InvalidDate(String message) {
        super("Invalid date");
    }

}
