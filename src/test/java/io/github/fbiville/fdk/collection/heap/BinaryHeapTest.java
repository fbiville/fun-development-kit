package io.github.fbiville.fdk.collection.heap;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class BinaryHeapTest {

    @Test
    public void heap_without_contents_is_empty() {
        BinaryHeap<String> heap = new BinaryHeap<>(HeapOrder.MIN);

        assertThat(heap.isEmpty()).isTrue();
    }

    @Test
    public void peeks_max_element_of_max_heap() throws Exception {
        BinaryHeap<Integer> heap = new BinaryHeap<>(HeapOrder.MAX, 1, 2, 1, 2, 1, 3);

        Optional<Integer> element = heap.peek();

        assertThat(element).isPresent();
        assertThat(element.get()).isEqualTo(3);
    }

    @Test
    public void peeks_min_element_of_min_heap() throws Exception {
        BinaryHeap<Integer> heap = new BinaryHeap<>(HeapOrder.MIN, 1, 3, 2, 5, 2, 3);

        Optional<Integer> element = heap.peek();

        assertThat(element).isPresent();
        assertThat(element.get()).isEqualTo(1);
    }

    @Test
    public void pops_max_element_of_max_heap() {
        BinaryHeap<Integer> integers = new BinaryHeap<>(HeapOrder.MAX, 1, 2, 3, 4);

        assertThat(integers.pop()).isPresent().contains(4);
        assertThat(integers.pop()).isPresent().contains(3);
        assertThat(integers.pop()).isPresent().contains(2);
        assertThat(integers.pop()).isPresent().contains(1);
        assertThat(integers.pop()).isEmpty();
    }

    @Test
    public void pops_min_element_of_min_heap() {
        BinaryHeap<Integer> integers = new BinaryHeap<>(HeapOrder.MIN, 1, 2, 3, 4);

        assertThat(integers.pop()).isPresent().contains(1);
        assertThat(integers.pop()).isPresent().contains(2);
        assertThat(integers.pop()).isPresent().contains(3);
        assertThat(integers.pop()).isPresent().contains(4);
        assertThat(integers.pop()).isEmpty();
    }
}