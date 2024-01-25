package org.example;

import java.util.List;
public class ListComparator {
    public <T extends Number, S extends Number> void compareList(final List<T> o1, final List<S> o2) {
        switch (compareAverage(o1, o2)) {
            case 1 -> System.out.println("Первый список имеет большее среднее значение");
            case -1 -> System.out.println("Второй список имеет большее среднее значение");
            default -> System.out.println("Средние значения равны");
        }
    }
    public <T extends Number, S extends Number> int compareAverage(final List<T> o1, final List<S> o2) {
        if (o1 == o2) {
            return 0;
        }
        double result = getAverageList(o1) - getAverageList(o2);
        return result > 0 ? 1 : result < 0 ? -1 : 0;
    }
    public <T extends Number> double getAverageList(final List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("A list can be null.");
        }
        return list.stream().mapToDouble(Number::doubleValue).average().getAsDouble();
    }

}
