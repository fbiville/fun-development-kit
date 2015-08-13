package io.github.fbiville.fdk.heap;

import io.github.fbiville.fdk.heap.BinaryHeap;
import io.github.fbiville.fdk.heap.BinaryHeapPrinter;
import io.github.fbiville.fdk.heap.HeapOrder;
import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class BinaryHeapPrinterTest {

    private BinaryHeapPrinter<Integer> printer = new BinaryHeapPrinter<>();

    @Test
    public void prints_empty_string_for_empty_heap() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(HeapOrder.MIN);

        assertThat(printer.print(heap)).isEmpty();
    }

    @Test
    public void prints_full_max_binary_heap() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(HeapOrder.MAX, 1, 2, 3);

        String result = printer.print(heap);

        assertThat(result).isEqualTo(
                "_3_\n"
              + "2_1\n");
    }

    @Test
    public void prints_full_min_binary_heap() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(HeapOrder.MIN, 1, 2, 3);

        String result = printer.print(heap);

        assertThat(result).isEqualTo(
                "_1_\n"
              + "2_3\n");
    }

    @Test
    public void prints_incomplete_max_heap() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(HeapOrder.MAX, 1, 2);

        String result = printer.print(heap);

        assertThat(result).isEqualTo(
                "_2_\n"
              + "1__\n");
    }

    @Test
    public void prints_incomplete_min_heap() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(HeapOrder.MIN, 1, 2);

        String result = printer.print(heap);

        assertThat(result).isEqualTo(
                "_1_\n"
              + "2__\n");
    }
}