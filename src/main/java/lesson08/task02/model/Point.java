package lesson08.task02.model;

import java.io.Serializable;

/**
 * Created by Pavel Borodin on 09.06.2019.
 */
public class Point implements Serializable {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
