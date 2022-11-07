package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfter4() {
        ListUtils.addAfter(input, 1, 4);
        assertThat(input).hasSize(3).containsSequence(1, 3, 4);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.removeIf(input, s -> s.equals(3));
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void whenRemoveIf2() {
        List<Integer> in = new ArrayList<>(Arrays.asList(1, 2, 1, 2));
        ListUtils.removeIf(in, s -> s.equals(2));
        assertThat(in).hasSize(2).containsSequence(1, 1);
    }

    @Test
    void whenReplaceIf() {
        List<Integer> in = new ArrayList<>(Arrays.asList(1, 2, 1, 2));
        ListUtils.replaceIf(in, s -> s.equals(2), 1);
        assertThat(in).hasSize(4).containsSequence(1, 1, 1, 1);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> in = new ArrayList<>(Arrays.asList(1, 2, 1, 2));
        ListUtils.removeAll(input, in);
        assertThat(input).hasSize(1).containsSequence(3);
    }
}