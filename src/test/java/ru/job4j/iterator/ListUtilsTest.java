package ru.job4j.iterator;

import static org.hamcrest.Matchers.is;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveAllElementsFromList() {
        List<Integer> originalList = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        List<Integer> overList = new ArrayList<>(Arrays.asList(0, 3));
        ListUtils.removeAll(originalList, overList);
        assertThat(originalList, is(Arrays.asList(1, 2, 4)));
    }

    @Test
    public void whenRemoveElementsByPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        ListUtils.removeIf(input, (a -> a % 2 == 1));
        assertThat(input, is(Arrays.asList(2, 4, 6, 8, 10)));
    }

    @Test
    public void whenReplaceElementsByPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        ListUtils.replaceIf(input, (a -> a % 2 == 1), 11);
        assertThat(input, is(Arrays.asList(11, 2, 11, 4, 11, 6, 11, 8, 11, 10)));
    }

}