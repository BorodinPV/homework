package lesson09.task01;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel Borodin on 11.08.2019.
 */
public class Main {

    private static final String PATH_JAVA_FILE = "./src/main/java/lesson09/task01/SomeClass.java";

    public static void main(String[] args) {
        List<String> fields = readFromConsole();
        javaClassFile(fields);
        runCode();
    }

    /**
     * Чтение с консоли
     *
     * @return возвращает массив полей
     * @author Pavel Borodin
     */
    private static List<String> readFromConsole() {
        List<String> fields = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        try {
            while (!(str = reader.readLine()).isEmpty()) {
                fields.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return fields;
    }

    /**
     * Добавляем строчки код в класс
     *
     * @param fields
     * @author Pavel Borodin
     */
    private static void javaClassFile(List<String> fields) {
        StringBuilder sb = null;
        try {
            List<String> lines = Files.readAllLines(Paths.get(Main.PATH_JAVA_FILE));
            sb = new StringBuilder();

            for (String s : lines) {
                if (s.contains("doWork()")) {
                    sb.append(s).append("\n");
                    getFields(fields, sb);
                } else {
                    sb.append(s).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (sb != null) {
            try (FileOutputStream fos = new FileOutputStream(Main.PATH_JAVA_FILE)) {
                fos.write(sb.toString().getBytes());
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Отдаем поля которые были считаны с клавиатуры
     *
     * @param fields
     * @param stringBuilder
     * @author Pavel Borodin
     */
    private static void getFields(List<String> fields, StringBuilder stringBuilder) {
        for (String field : fields) {
            stringBuilder.append(field);
        }
    }

    /**
     * Загружаем и компилируем класс
     *
     * @author Pavel Borodin
     */
    private static void runCode() {
        JavaCompiler jCompiler = ToolProvider.getSystemJavaCompiler();
        jCompiler.run(System.in, System.out, System.err, Paths.get(Main.PATH_JAVA_FILE).toFile().getAbsolutePath());

        String pathToClassFile = Main.PATH_JAVA_FILE.substring(0, Main.PATH_JAVA_FILE.length() - 5).concat(".class");
        ClassLoader classLoader = new MyClassLoader(pathToClassFile);
        try {
            Class<?> someClassClass = classLoader.loadClass("lesson09.task01.SomeClass");
            Worker someWorker = (Worker) someClassClass.newInstance();
            someWorker.doWork();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}


