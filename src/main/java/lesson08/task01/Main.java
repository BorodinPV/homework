package lesson08.task01;

import lesson08.task01.model.Person;
import lesson08.task01.service.MagicSerializeImpl;

/**
 * Created by Pavel Borodin on 26.05.2019.
 */
public class Main {

    public static void main(String[] args) {
        String FILE_PATH = "./src/main/java/lesson08/task01/resources/";
        String FILE_NAME = "person.bin";

        Person person = new Person("Pavel", 26, "M");
        MagicSerializeImpl magicSerializeImpl = new MagicSerializeImpl(person, FILE_PATH + FILE_NAME);
        magicSerializeImpl.start();
    }
}

