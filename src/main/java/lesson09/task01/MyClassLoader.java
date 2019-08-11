package lesson09.task01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Pavel Borodin on 11.08.2019.
 */
public class MyClassLoader extends ClassLoader {
    private final String classFile;

    public MyClassLoader(String classFile) {
        this.classFile = classFile;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("lesson09.task01.SomeClass".equals(name))
            return findClass(name);
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if ("lesson09.task01.SomeClass".equals(name)) {
            try {
                byte[] classBytes = Files.readAllBytes(Paths.get(classFile));
                return defineClass(name, classBytes, 0, classBytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}
