package lesson05.task01;

import lesson05.task01.model.Animal;
import lesson05.task01.model.Person;
import lesson05.task01.model.Sex;
import lesson05.task01.model.Sort;
import lesson05.task01.service.CardFile;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.UUID;

/**
 * Created by Pavel Borodin on 05/05/19.
 */

public class Launch {

    private static final Logger log = Logger.getLogger(Launch.class);

    /**
     * @author Pavel Borodin
     * start fill the array
     */
    public void start() {
        CardFile cardFile = new CardFile();
        cardFile.addAnimal(
                "Test",
                new Animal("Гоша", new Person(33, Sex.MAN, "Игорь"),
                        10)
        );
        cardFile.addAnimal(
                UUID.randomUUID().toString(),
                new Animal("Гоша", new Person(33, Sex.MAN, "Олег"),
                        10)
        );
        cardFile.addAnimal(
                UUID.randomUUID().toString(),
                new Animal("Кеша", new Person(35, Sex.MAN, "Женя"),
                        15)
        );
        cardFile.addAnimal(
                UUID.randomUUID().toString(),
                new Animal("Барсик", new Person(21, Sex.WOMAN, "Лена"),
                        13)
        );
        cardFile.addAnimal(
                UUID.randomUUID().toString(),
                new Animal("Пушок", new Person(15, Sex.MAN, "Саша"),
                        20)
        );
        cardFile.addAnimal(
                UUID.randomUUID().toString(),
                new Animal("Снежок", new Person(25, Sex.MAN, "Андрей"),
                        17)
        );

        cardFile.dataChangeAnimal(
                "Test",
                new Animal("Негодяй", new Person(16, Sex.WOMAN, "Маша"),
                        18)
        );

        logInfoText("Поиск по кличке", cardFile.searchAnimal("Негодяй"));

        logInfoText("Сортировка по кличке", cardFile.sort(Sort.NAME));
        logInfoText("Сортировка по Хозяину", cardFile.sort(Sort.PERSON));
        logInfoText("Сортировка по весу животного", cardFile.sort(Sort.WEIGHT));
    }

    private void logInfoText(String message, List<Animal> animals) {
        log.info(message);
        for (Animal animal : animals) {
            log.info(animal.toString());
        }
    }
}