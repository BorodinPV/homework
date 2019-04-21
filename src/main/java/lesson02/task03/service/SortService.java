package lesson02.task03.service;

import lesson02.task03.exception.CustomException;
import lesson02.task03.helper.Utils;
import lesson02.task03.model.Person;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel Borodin on 22/4/19.
 */
public class SortService {
    private static final Logger log = Logger.getLogger(SortService.class);

    /**
     * @throws CustomException
     * @author Pavel Borodin
     * @see SortService#sort(List, Sort)
     */
    public void start() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Person person = new Person(Utils.getRandomAge(), Utils.getRandomSex(), Utils.getRandomName());
            if (people.contains(person)) {
                try {
                    throw new CustomException("Match people");
                } catch (CustomException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            people.add(person);
        }
        sort(people, new SortOne());
        sort(people, new SortTwo());
    }

    /**
     * @param people
     * @param clazz
     * @param <T>
     * @author Pavel Borodin
     */
    private <T extends Sort> void sort(List<Person> people, T clazz) {
        clazz.sortAge(people);
        getPersonLog(people);
        clazz.sortAlphabet(people);
        getPersonLog(people);
        clazz.sortSex(people);
        getPersonLog(people);
    }

    /**
     * @param people log info
     * @author Pavel Borodin
     */
    private void getPersonLog(List<Person> people) {
        for (Person person : people) {
            log.info(person);
        }
    }
}