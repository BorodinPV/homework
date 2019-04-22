package lesson04.task01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MathBox {

    private Set<Number> numbers;

    public MathBox(Number[] numbers) {
        this.numbers = new HashSet<>(Arrays.asList(numbers));
    }

    public Number summator() {
        return numbers.stream().reduce((number, number2) -> number.intValue() + number2.intValue()).orElse(0);
    }

    public void splitter(int divider) {
        numbers = numbers.stream().map(number -> number.doubleValue() / divider).collect(Collectors.toSet());
    }

    public void deleteNumber(int deleteNumber) {
        numbers = getNumbers().stream().filter(number -> !number.equals((double) deleteNumber)).collect(Collectors.toSet());
    }

    public Set<Number> getNumbers() {
        return numbers;
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
