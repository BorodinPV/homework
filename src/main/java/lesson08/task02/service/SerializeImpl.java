package lesson08.task02.service;

import lesson08.task01.exception.CustomException;
import lesson08.task01.service.Serialize;
import org.apache.log4j.Logger;

import java.io.*;
import java.time.LocalDateTime;

/**
 * Created by Pavel Borodin on 26.05.2019.
 */
public class SerializeImpl implements Serialize {

    private static final Logger log = Logger.getLogger(SerializeImpl.class);

    /**
     * @param object
     * @param file-path
     * @author Pavel Borodin
     */
    @Override
    public void serialize(Object object, String file) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(object);
            log.info("serialization successful: " + LocalDateTime.now());
        } catch (IOException e) {
            log.info("Error serialize");
        }
    }

    /**
     * @param file - path
     * @return Object
     * @author Pavel Borodin
     */
    @Override
    public Object deSerialize(String file) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            return ois.readObject();
        } catch (Exception e) {
            return new CustomException("Error deSerialize");
        }
    }
}
