package lesson05.task01.model;

import java.util.Objects;

/**
 * Created by Pavel Borodin on 05/05/19.
 */

public class Animal {
    private String name;
    private Person person;
    private int weight;

    public Animal(String name, Person person, int weight) {
        this.name = name;
        this.person = person;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Person getPerson() {
        return person;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return weight == animal.weight &&
                Objects.equals(name, animal.name) &&
                Objects.equals(person, animal.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, person, weight);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", person=" + person +
                ", weight=" + weight +
                '}';
    }
}
