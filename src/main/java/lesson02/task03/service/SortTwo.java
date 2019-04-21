package lesson02.task03.service;

import lesson02.task03.model.Person;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Pavel Borodin on 22/4/19.
 */
public class SortTwo implements Sort {

    @Override
    public List<Person> sortSex(List<Person> people) {
        return people.stream().sorted(Comparator.comparing(Person::getSex)).collect(Collectors.toList());
    }

    @Override
    public List<Person> sortAge(List<Person> people) {
        return people.stream().sorted(Comparator.comparing(Person::getAge).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<Person> sortAlphabet(List<Person> people) {
        return people.stream().sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList());
    }
}