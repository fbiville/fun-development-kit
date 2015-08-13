package io.github.fbiville.fdk.array;

import io.github.fbiville.fdk.array.ArrayIterator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class ArrayIteratorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void iterates_over_array() {
        Iterator<Integer> iterator = new ArrayIterator<>(new Integer[]{1, 2, 3});

        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void has_no_next_elements_for_empty_arrays() {
        ArrayIterator<String> iterator = new ArrayIterator<>(new String[0]);

        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void fails_to_retrieve_next_element_for_empty_array() {
        ArrayIterator<String> iterator = new ArrayIterator<>(new String[0]);

        thrown.expect(NoSuchElementException.class);

        iterator.next();
    }
}