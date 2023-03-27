package academy.mindswap.finalproject.exceptions;

public class FitnessTestAlreadySchedule extends RuntimeException{

    public FitnessTestAlreadySchedule(String message) {
        super("Fitness Test already schedule");
    }
}
