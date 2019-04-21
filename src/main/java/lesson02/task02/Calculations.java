package lesson02.task02;

import lesson02.task02.exception.CustomException;
import lesson02.task02.helper.Operators;
import org.apache.log4j.Logger;

import java.util.Random;

/**
 * Created by Pavel Borodin on 22/4/19.
 */
public class Calculations {
    private static final Logger log = Logger.getLogger(Calculations.class);

    /**
     * @author Pavel Borodin
     * start generation numbers
     */
    public void start() {
        int n;
        Random random = new Random();
        do {
            n = random.nextInt();
            System.out.println(n);
        } while (n < 0);
        log.info("Random number: " + n);

        //прогреваем пеку из-за размытости задания
        for (int i = 0; i < n; i++) {
            int randomNumber = random.nextInt();
            if (randomNumber < 0) {
                try {
                    throw new CustomException("Random number is negative");
                } catch (CustomException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            double sqrt = Operators.getSqrt(randomNumber);
            double pov = Operators.getPov((int) sqrt);
            if ((int) pov == randomNumber) {
                log.info("root integer" + randomNumber);
            }
        }
    }
}