package lesson08.task02.service;

import lesson08.task02.model.Line;
import org.apache.log4j.Logger;

/**
 * Created by Pavel Borodin on 09.06.2019.
 */
public class MagicSerializeImpl {

    private static final Logger log = Logger.getLogger(MagicSerializeImpl.class);
    private Line line;
    private String filePath;

    public MagicSerializeImpl(Line line, String filePath) {
        this.line = line;
        this.filePath = filePath;
    }

    public void start() {
        SerializeImpl serializeImpl = new SerializeImpl();
        serializeImpl.serialize(getLine(), getFilePath());
        log.info(serializeImpl.deSerialize(getFilePath()).toString());
    }

    public Line getLine() {
        return line;
    }

    public String getFilePath() {
        return filePath;
    }
}
