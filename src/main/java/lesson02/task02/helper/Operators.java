package lesson02.task02.helper;

/**
 * Created by Pavel Borodin on 22/4/19.
 */

public class Operators {

    /**
     * @author Pavel Borodin
     * @param randomNumber
     * @return sqrt
     */
    public static double getSqrt(int randomNumber) {
        return Math.sqrt(randomNumber);
    }

    /**
     * @author Pavel Borodin
     * @param sqrt
     * @return pov
     */
    public static double getPov(double sqrt) {
        return Math.pow(sqrt, 2);
    }
}