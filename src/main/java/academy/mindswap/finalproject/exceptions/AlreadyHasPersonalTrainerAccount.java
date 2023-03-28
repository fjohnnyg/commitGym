package academy.mindswap.finalproject.exceptions;

public class AlreadyHasPersonalTrainerAccount extends RuntimeException{
    public AlreadyHasPersonalTrainerAccount(String message) {
        super("The user already have a personal trainer account");

    }
}
