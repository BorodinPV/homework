package lesson04.task02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel Borodin on 22/4/19.
 */

public class ObjectBox {

    private List<Object> objects;

    public ObjectBox() {
        this.objects = new ArrayList<>();
    }

    /**
     * @param object #add-object
     * @author Pavel Borodin
     */
    public void addObject(Object object) {
        objects.add(object);
    }

    /**
     * @param object #dell-object
     * @author Pavel Borodin
     */
    public void deleteObject(Object object) {
        objects.remove(object);
    }

    /**
     * @return objects
     * @author Pavel Borodin
     */
    public String dump() {
        return "ObjectBox{" +
                "objects=" + objects +
                '}';
    }
}