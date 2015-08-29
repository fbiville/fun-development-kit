package io.github.fbiville.fdk.collection.array;

import io.github.fbiville.fdk.collection.heap.BinaryHeap;
import io.github.fbiville.fdk.collection.heap.HeapOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class DynamicArrayTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void grows_array_size_at_creation() {
        DynamicArray<Integer> integers = new DynamicArray<>(1, 2, 3);

        assertThat(integers.size()).isEqualTo(3);
        assertThat(integers.getInternalStorage())
                .hasSize(6)
                .containsExactly(1, 2, 3, null, null, null);
    }

    @Test
    public void grows_when_adding_to_full_array() {
        DynamicArray<String> strings = new DynamicArray<>();
        assertThat(strings.isEmpty()).isTrue();

        DynamicArray<String> result = strings.add("a string");
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.getInternalStorage())
                .hasSize(2)
                .containsExactly("a string", null);
    }

    @Test
    public void shrinks_when_removing_from_fragmented_array() {
        DynamicArray<Character> chars = new DynamicArray<>('a', 'b', 'c', 'd');
        assertThat(chars.getInternalStorage()).hasSize(8);

        DynamicArray<Character> result = chars.remove(0).remove(1);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.getInternalStorage())
                .hasSize(4)
                .containsExactly('c', 'd', null, null);
    }

    @Test
    public void gets_element_from_index() {
        DynamicArray<Double> doubles = new DynamicArray<>(2.0d, 3.0d, 4.0d, 5.0d);

        assertThat(doubles.get(0)).isPresent().contains(2.0d);
        assertThat(doubles.remove(0).get(0)).isEmpty();
    }

    @Test
    public void returns_index_of_element() {
        DynamicArray<String> strings = new DynamicArray<>("hello", "world");

        assertThat(strings.indexOf("world")).isPresent().contains(1);
        assertThat(strings.indexOf("foo")).isEmpty();
    }

    @Test
    public void returns_last_index_of_element() {
        DynamicArray<Boolean> booleans = new DynamicArray<>(true, true, true, true);

        assertThat(booleans.lastIndexOf(true)).isPresent().contains(3);
        assertThat(booleans.lastIndexOf(false)).isEmpty();
    }

    @Test
    public void array_contains_added_element() {
        DynamicArray<Boolean> booleans = new DynamicArray<>(true);

        assertThat(booleans.contains(true)).isTrue();
        assertThat(booleans.contains(false)).isFalse();
    }

    @Test
    public void illegal_index_access_when_getting_element_from_empty_array() {
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        new DynamicArray<String>().get(0);
    }

    @Test
    public void illegal_index_access_when_getting_from_negative_index() {
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        new DynamicArray<>("aha").get(-1);
    }

    @Test
    public void illegal_index_access_when_getting_from_index_beyond_size() {
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        new DynamicArray<>("aha").get(2);
    }

    @Test
    public void iterates_on_actual_contents() {
        BinaryHeap<Integer> integers = new BinaryHeap<>(HeapOrder.MAX, 1, 2, 3, 4);

        Collection<Integer> result = new ArrayList<>();
        Iterator<Integer> iterator = integers.iterator();
        result.add(iterator.next());
        result.add(iterator.next());
        result.add(iterator.next());
        result.add(iterator.next());
        assertThat(iterator.hasNext()).isFalse();

        assertThat(result)
                .startsWith(4)
                .hasSize(4)
                .contains(1,2,3,4);
    }
}