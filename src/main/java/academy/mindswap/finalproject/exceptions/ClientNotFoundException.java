package academy.mindswap.finalproject.exceptions;

    public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException() {
        super("User not found");
    }
}
