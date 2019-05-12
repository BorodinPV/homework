package lesson06.task01;

import lesson06.task01.service.LaunchService;

import java.util.Set;

/**
 * Created by Pavel Borodin on 12.05.2019.
 */
public class Main {
    public static void main(String[] args) {
        LaunchService launchService = new LaunchService();
        Set<String> words = launchService.readFile("./src/main/java/lesson06/task01/resources/note.txt");
        launchService.writeFile("./src/main/java/lesson06/task01/resources/note2.txt",  launchService.sortWords(words));
    }
}