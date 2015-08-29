package io.github.fbiville.fdk.collection.sort;

import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.StrictAssertions.assertThat;

abstract class SortTestCases {

    @Test
    public void returns_small_sorted_arrays_as_is() {
        assertThat(sort(new Character[]{})).isEmpty();
        assertThat(sort(new Character[]{'a'})).containsExactly('a');
    }

    @Test
    public void sorts_arrays() {
        assertThat(sort(new Integer[]{3, 2})).containsExactly(2,3);
        assertThat(sort(new Integer[]{3, 2, 1})).containsExactly(1,2,3);
        assertThat(sort(new Integer[]{2, 21, -1, 4, -6})).containsExactly(-6, -1, 2, 4, 21);
    }

    @Test(timeout = 30000)
    public void sorts_long_arrays() {
        Integer[] integers = random_array(10_000_000);

        assertThat(sort(integers)).isSorted();
    }

    protected abstract <T extends Comparable<T>> T[] sort(T[] array);

    private Integer[] random_array(int length) {
        Integer[] array = new Integer[length];
        Random random = new Random(2*length);
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(8*length);
        }
        return array;
    }

}
