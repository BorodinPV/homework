package lesson02.task01;

import lesson02.task01.exception.CustomException;
import org.apache.log4j.Logger;

/**
 * Created by Pavel Borodin on 22/4/19.
 */
public class Errors {
    private static final Logger log = Logger.getLogger(Errors.class);

    public void modelingErrors() {
        //Финт исключительно для ДЗ
        Object[] objects = {null};
        modelingNullPointerException(objects);
        modelingArrayIndexOutOfBoundsException(objects);
        modelingCustomException();
    }

    public void modelingNullPointerException(Object[] objects) {
        try {
            log.info(objects[0].toString());
        } catch (NullPointerException e) {
            log.error("Hello, World!");
            e.printStackTrace();
        }
    }

    public void modelingArrayIndexOutOfBoundsException(Object[] objects) {
        try {
            log.info(objects[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Hello, World!");
            e.printStackTrace();
        }
    }

    public void modelingCustomException() {
        try {
            throw new CustomException("Custom Exception");
        } catch (CustomException e) {
            log.error("Hello, World!");
            e.printStackTrace();
        }
    }
}