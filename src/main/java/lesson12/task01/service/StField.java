package lesson12.task01.service;

import javassist.CannotCompileException;
import javassist.ClassPool;

/**
 * Created by Pavel Borodin on 2019-06-16
 */
public class StField {

    static ClassPool classPool = javassist.ClassPool.getDefault();

    /**
     * @text метод для получения ошибки в Heap-Space
     * @author Pavel Borodin
     */
    public static void createHeapSpaceError() throws InterruptedException {
        Thread.sleep(10000);
        int multiplier = 10;
        for (int i = 1; i < 100; i++) {
            Thread.sleep(1);
            int[] myIntList = new int[multiplier];
            for (int j = i; j > 1; j--) {
                myIntList[j] = i;
            }
            multiplier = multiplier * 10;
        }
    }

    /**
     * @text метод для получения ошибки в Metaspace, GC отработывает как и в задании
     * @author Pavel Borodin
     */
    public static void createMetaspaceError() {

        int core = Runtime.getRuntime().availableProcessors();

        for (int i = 0; i < 1000000000; i++) {
            Class c = null;
            try {
                for (int j = 0; j < core; j++) {
                    c = classPool.makeClass("homework.lesson12.task01.service.StField" + j).toClass();
                }
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }
            assert c != null;
            System.out.println(c.getName());
        }
    }
}
