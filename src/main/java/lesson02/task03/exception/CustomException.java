package lesson02.task03.exception;

/**
 * Created by Pavel Borodin on 22/4/19.
 */
public class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}