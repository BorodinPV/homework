package lesson08.task01.exception;

/**
 * Created by Pavel Borodin on 26.05.2019.
 */
public class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}