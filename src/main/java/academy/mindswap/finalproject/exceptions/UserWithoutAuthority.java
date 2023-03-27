package academy.mindswap.finalproject.exceptions;

public class UserWithoutAuthority extends RuntimeException{

    public UserWithoutAuthority(String message) {
            super("User without authority");
        }
}
