package lesson04.task01;

import lesson04.task01.exception.CustomException;
import lesson04.task02.ObjectBox;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Pavel Borodin on 22/4/19.
 */


public class MathBox<T extends Number> extends ObjectBox {

    private Set<Number> numbers;

    public MathBox(T[] numbers) {
        this.numbers = new HashSet<>(Arrays.asList(numbers));
    }

    //Для проверки 3 задания
    public MathBox() {
    }

    /**
     * @return summ
     * @author Pavel Borodin
     */
    public Number summator() {
        return numbers.stream().filter(number -> !Objects.isNull(number))
                .reduce((number, number2) -> number.doubleValue() + number2.doubleValue())
                .orElse(0);
    }

    /**
     * @param divider
     * @author Pavel Borodin
     */
    public void splitter(double divider) {
        numbers = numbers.stream().filter(number -> !Objects.isNull(number))
                .map(number -> number.doubleValue() / divider)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * @param deleteNumber
     * @author Pavel Borodin
     */
    public void deleteNumber(int deleteNumber) {
        numbers = getNumbers().stream().filter(number -> !number.equals((double) deleteNumber))
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * @return numbers
     * @author Pavel Borodin
     */
    public Set<Number> getNumbers() {
        return numbers;
    }

    @Override
    public void addObject(Object object) {
        try {
            throw new CustomException("Attention!");
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox mathBox = (MathBox) o;
        return Objects.equals(numbers, mathBox.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "numbers=" + numbers +
                '}';
    }
}
