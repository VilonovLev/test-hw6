package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ListComparatorTest {
    ListComparator listComparator = new ListComparator();;
    List<Integer> first;
    List<Double> second;
    List<Long> third;

    @BeforeEach
    void init() {
        first = new ArrayList<>(Arrays.asList(1,2,3));
        second = new ArrayList<>(Arrays.asList(2.5,1.5,2d));
        third = new ArrayList<>(List.of(6L,10L));
    }

    @Test
    void getAverageTest() {
        assertThat(listComparator.getAverageList(first)).isEqualTo(2);
        assertThat(listComparator.getAverageList(second)).isEqualTo(2);
        assertThat(listComparator.getAverageList(third)).isEqualTo(8);
    }

    @Test
    void throwGetAverageTest() {
        assertThrows(IllegalArgumentException.class, () -> {listComparator.getAverageList(null);});
    }

    @Test
    void compareAverageTest() {
        assertThat(listComparator.compareAverage(first, first)).isEqualTo(0);
        assertThat(listComparator.compareAverage(first, second)).isEqualTo(0);
        assertThat(listComparator.compareAverage(third, second)).isEqualTo(1);
        assertThat(listComparator.compareAverage(first, third)).isEqualTo(-1);
    }

    @Test
    void compareListTest() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        listComparator.compareList(first, second);
        assertThat(byteArrayOutputStream.toString().trim()).isEqualTo("Средние значения равны");
        byteArrayOutputStream.reset();
        listComparator.compareList(third, second);
        assertThat(byteArrayOutputStream.toString().trim()).isEqualTo("Первый список имеет большее среднее значение");
        byteArrayOutputStream.reset();
        listComparator.compareList(first, third);
        assertThat(byteArrayOutputStream.toString().trim()).isEqualTo("Второй список имеет большее среднее значение");
    }


}
