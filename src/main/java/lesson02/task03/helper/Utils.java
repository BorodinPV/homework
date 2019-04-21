package lesson02.task03.helper;

import lesson02.task03.model.Sex;

import java.util.Random;

/**
 * Created by Pavel Borodin on 22/4/19.
 */
public class Utils {

    /**
     * @author Pavel Borodin
     * @return Age
     */
    public static int getRandomAge() {
        int min = 1;
        int max = 10;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        return i;
    }

    /**
     * @author Pavel Borodin
     * @return Sex
     */
    public static Sex getRandomSex() {
        int pick = new Random().nextInt(Sex.values().length);
        return Sex.values()[pick];
    }

    /**
     * @author Pavel Borodin
     * @return Name
     */
    public static String getRandomName() {
        String[] names = {"Name1", "Name2", "Name3", "Name4", "Name5", "Name6", "Name7", "Name8", "Name9", "Name10"};
        int random = (int) (Math.random() * names.length);
        return names[random];
    }
}