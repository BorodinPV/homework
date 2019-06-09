package lesson08.task01.service;

/**
 * Created by Pavel Borodin on 09.06.2019.
 */
public interface Serialize {
    void serialize (Object object, String file);
    Object deSerialize(String file);
}
