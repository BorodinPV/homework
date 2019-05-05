package lesson05.task01.service;

import lesson05.task01.model.Animal;
import lesson05.task01.model.Sort;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Pavel Borodin on 05/05/19.
 */

public class CardFile {

    private HashMap<String, Animal> animals;

    public CardFile() {
        this.animals = new HashMap<>();
    }

    /**
     * @param identifier
     * @param animal
     * @author Pavel Borodin
     */
    public void addAnimal(String identifier, Animal animal) {
        animals.put(identifier, animal);
    }

    /**
     * @param name
     * @return animals
     * @author Pavel Borodin
     */
    public List<Animal> searchAnimal(String name) {
        return animals.entrySet().parallelStream()
                .filter(fl -> fl.getValue().getName().equals(name))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    /**
     * @param identifier
     * @param animal
     * @author Pavel Borodin
     */
    public void dataChangeAnimal(String identifier, Animal animal) {
        animals.replace(identifier, animal);
    }

    /**
     * @param sort
     * @return animals
     * @author Pavel Borodin
     */
    public List<Animal> sort(Sort sort) {
        switch (sort) {
            case PERSON:
                return this.animals.values().stream().sorted(Comparator.comparing(animal -> animal.getPerson().getName())).collect(Collectors.toList());
            case NAME:
                return this.animals.values().stream().sorted(Comparator.comparing(Animal::getName)).collect(Collectors.toList());
            case WEIGHT:
                return this.animals.values().stream().sorted(Comparator.comparing(Animal::getWeight)).collect(Collectors.toList());
            default:
                return new ArrayList<>(this.animals.values());
        }
    }
}