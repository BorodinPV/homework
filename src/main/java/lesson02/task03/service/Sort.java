package lesson02.task03.service;

import lesson02.task03.model.Person;

import java.util.List;

/**
 * Created by Pavel Borodin on 22/4/19.
 */
public interface Sort {

    List<Person> sortSex(List<Person> people);

    List<Person> sortAge(List<Person> people);

    List<Person> sortAlphabet(List<Person> people);
}