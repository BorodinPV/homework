package lesson08.task01.service;

import lesson08.task01.model.Person;
import org.apache.log4j.Logger;

/**
 * Created by Pavel Borodin on 09.06.2019.
 */
public class MagicSerializeImpl {

    private static final Logger log = Logger.getLogger(MagicSerializeImpl.class);

    private Person person;
    private String filePath;

    public MagicSerializeImpl(Person person, String filePath) {
        this.person = person;
        this.filePath = filePath;
    }

    public void start() {
        SerializeImpl serializeImpl = new SerializeImpl();
        serializeImpl.serialize(getPerson(), getFilePath());
        log.info(serializeImpl.deSerialize(getFilePath()).toString());
    }

    public Person getPerson() {
        return person;
    }

    public String getFilePath() {
        return filePath;
    }
}
