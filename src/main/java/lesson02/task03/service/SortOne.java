package lesson02.task03.service;

import lesson02.task03.model.Person;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Pavel Borodin on 22/4/19.
 */
public class SortOne implements Sort {

    @Override
    public List<Person> sortSex(List<Person> people) {
        people.sort(Comparator.comparing(Person::getSex));
        return people;
    }

    @Override
    public List<Person> sortAge(List<Person> people) {
        people.sort(Comparator.comparing(Person::getAge).reversed());
        return people;
    }

    @Override
    public List<Person> sortAlphabet(List<Person> people) {
        people.sort(Comparator.comparing(Person::getName));
        return people;
    }
}