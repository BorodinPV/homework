package lesson04.task01;

import org.apache.log4j.Logger;

/**
 * Created by Pavel Borodin on 22/4/19.
 */

public class Calculations {
    private static final Logger log = Logger.getLogger(lesson02.task02.Calculations.class);

    /**
     * @author Pavel Borodin
     * start generation numbers
     */
    public void start() {
        Number[] numbers = {1, 2, 3, 4, 5, 5, 5, null};
        MathBox<Number> mathBox = new MathBox<>(numbers);
        log.info("summator = " + mathBox.summator());
        mathBox.splitter(2);
        log.info("mathBox = " + mathBox);
        mathBox.deleteNumber(2);
        log.info("mathBox = " + mathBox);
    }
}
