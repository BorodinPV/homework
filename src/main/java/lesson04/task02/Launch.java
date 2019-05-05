package lesson04.task02;

import org.apache.log4j.Logger;

/**
 * Created by Pavel Borodin on 22/4/19.
 */
public class Launch {
    private static final Logger log = Logger.getLogger(Launch.class);

    /**
     * @author Pavel Borodin
     * start fill the array
     */
    public void start() {
        ObjectBox objectBox = new ObjectBox();
        objectBox.addObject("4");
        objectBox.addObject("3");
        objectBox.deleteObject("2");
        log.info(objectBox.dump());
    }
}