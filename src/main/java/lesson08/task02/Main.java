package lesson08.task02;

import lesson08.task02.model.Line;
import lesson08.task02.model.Point;
import lesson08.task02.service.MagicSerializeImpl;

/**
 * Created by Pavel Borodin on 09.06.2019.
 */
public class Main {

    public static void main(String[] args) {

        String FILE_PATH = "./src/main/java/lesson08/task02/resources/";
        String FILE_NAME = "person.bin";

        Point point1 = new Point(1.0, 1.0D);
        Point point2 = new Point(2.0, 2.0);
        Line line = new Line(point1, point2);

        MagicSerializeImpl magicSerialize = new MagicSerializeImpl(line, FILE_PATH + FILE_NAME);
        magicSerialize.start();
    }
}
