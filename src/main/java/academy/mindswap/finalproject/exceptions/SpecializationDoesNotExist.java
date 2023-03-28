package academy.mindswap.finalproject.exceptions;

public class SpecializationDoesNotExist extends RuntimeException{

    public SpecializationDoesNotExist() {
        super("Specialization does not exist in specializations list");
    }
}
