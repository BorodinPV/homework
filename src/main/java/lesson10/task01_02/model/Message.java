package lesson10.task01_02.model;

import java.io.Serializable;

/**
 * Created by Pavel Borodin on 2019-06-10
 */
public class Message implements Serializable {
    private String name;

    public Message(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
