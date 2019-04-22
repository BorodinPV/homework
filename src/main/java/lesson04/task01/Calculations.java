package lesson04.task01;

import org.apache.log4j.Logger;

public class Calculations {
    private static final Logger log = Logger.getLogger(lesson02.task02.Calculations.class);

    public void start() {
        Number[] numbers = {1, 2, 3, 4, 5, 5, 5};
        MathBox mathBox = new MathBox(numbers);
        log.info("summator = " + mathBox.summator());
        mathBox.splitter(2);
        log.info("mathBox = " + mathBox);
        mathBox.deleteNumber(2);
        log.info("mathBox = " + mathBox);
    }
}
