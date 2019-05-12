package lesson06.task01.service;

import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Pavel Borodin on 12.05.2019.
 */
public class LaunchService {
    private static final Logger log = Logger.getLogger(lesson02.task02.Calculations.class);

    /**
     * @param filename
     * @author Pavel Borodin
     */
    public Set<String> readFile(String filename) {
        log.info("Read file");
        Set<String> words = new HashSet<>();
        try {
            for (String s : Files.readAllLines(Paths.get(filename))) {
                String[] split = s.split("\\s");
                words.addAll(new HashSet<>(Arrays.asList(split)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    /**
     * @param words
     * @return words
     * @author Pavel Borodin
     */
    public List<String> sortWords(Set<String> words) {
        log.info("Sort file");
        return words.stream().sorted().collect(Collectors.toList());
    }

    /**
     * @param fileName
     * @param content
     * @author Pavel Borodin
     */
    public void writeFile(String fileName, List<String> content) {
        log.info("Write file");
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            for (String s : content) {
                byte[] buffer = s.getBytes();
                fileOutputStream.write(buffer, 0, buffer.length);
                fileOutputStream.write('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}