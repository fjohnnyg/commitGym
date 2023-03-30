package academy.mindswap.finalproject.aspects;

import academy.mindswap.finalproject.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@ControllerAdvice
public class GenericExceptionHandler {

    Logger logger =  LoggerFactory.getLogger(GenericExceptionHandler.class);


    @ExceptionHandler({UserNotMatch.class})
    public ResponseEntity<String> handleUserNotMatch(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("You are trying to access other User");
    }

    @ExceptionHandler({DailyPlanAlreadyExist.class})
    public ResponseEntity<String> DailyPlanAlreadyExistException(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("This client already have a daily plan for this date");
    }

    @ExceptionHandler({DailyPlanNotFoundException.class})
    public ResponseEntity<String> handleDailyPlanNotFound(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Daily-plan not found");
    }

    @ExceptionHandler({ExerciseAlreadyExist.class})
    public ResponseEntity<String> handleExerciseAlreadyExist(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exercise already exist");
    }

    @ExceptionHandler({ExerciseNotFound.class})
    public ResponseEntity<String> handleExerciseNotFound(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise not found");
    }

    @ExceptionHandler({FitnessTestAlreadySchedule.class})
    public ResponseEntity<String> handleFitnessTestAlreadySchedule(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fitness Test already schedule");
    }

    @ExceptionHandler({InactiveUser.class})
    public ResponseEntity<String> handleInactiveUser(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inactive user");
    }

    @ExceptionHandler({SpecializationDoesNotExist.class})
    public ResponseEntity<String> handleSpecializationDoesNotExist(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Specialization does not exist on specializations list");
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<String> handleUserNotFound(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @ExceptionHandler({UserWithoutAuthority.class})
    public ResponseEntity<String> handleUserWithoutAuthority(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User without authority");
    }

    @ExceptionHandler({FitnessTestNotFoundException.class})
    public ResponseEntity<String> handleFitnessTestNotFound(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fitness Test not found");
    }

    @ExceptionHandler({AlreadyHasPersonalTrainerAccount.class})
    public ResponseEntity<String> handleAlreadyHasPersonalTrainerAccount(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You already have a personal trainer account");
    }
    @ExceptionHandler({FitnessTestAlreadyDone.class})
    public ResponseEntity<String> handleFitnessTestAlreadyDone(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The Fitness Test of this date is already done");
    }

    @ExceptionHandler({InvalidDate.class})
    public ResponseEntity<String> handleInvalidDate(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid date");
    }
    @ExceptionHandler({WorkoutDoesNotExist.class})
    public ResponseEntity<String> handleWorkoutDoesNotExist(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Workout does not exist");
    }

    @ExceptionHandler({PersonalTrainerNotFoundException.class})
    public ResponseEntity<String> handlePersonalTrainerNotFound(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personal trainer not found");
    }

    @ExceptionHandler({ClientNotFoundException.class})
    public ResponseEntity<String> handleClientNotFound(Exception ex) {
        logger.error("Resource not found: " + ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
    }



    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> handleGenericException(Exception ex) {
        logger.error("Unknown Exception: " + ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
    }


}
