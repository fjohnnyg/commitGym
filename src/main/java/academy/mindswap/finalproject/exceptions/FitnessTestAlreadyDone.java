package academy.mindswap.finalproject.exceptions;

public class FitnessTestAlreadyDone extends RuntimeException{

    public FitnessTestAlreadyDone (String message) {
        super("The Fitness Test of this date is already done");
    }
}
