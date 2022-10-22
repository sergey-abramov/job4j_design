package ru.job4j.assertj;

import com.sun.source.doctree.SeeTree;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).isNotNull()
                .hasSize(5)
                .contains("three")
                .contains("second", Index.atIndex(1))
                .doesNotContain("second", Index.atIndex(3));
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("five", "four", "three", "second", "first");
        assertThat(set).isNotNull()
                .hasSize(5)
                .contains("five")
                .doesNotContain("six");

    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four");
        assertThat(map).isNotNull()
                .hasSize(4)
                .containsEntry("first", 0)
                .containsKeys("first", "second")
                .containsValues(0, 1, 2, 3);

    }
}