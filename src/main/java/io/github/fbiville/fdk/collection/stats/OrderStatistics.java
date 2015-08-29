package io.github.fbiville.fdk.collection.stats;

import io.github.fbiville.fdk.collection.Tuple2;
import io.github.fbiville.fdk.collection.heap.BinaryHeap;
import io.github.fbiville.fdk.collection.heap.HeapOrder;

import java.util.Optional;

public class OrderStatistics<T extends Comparable<T>> {

    private final T[] elements;

    @SafeVarargs
    public OrderStatistics(T... elements) {
        this.elements = elements;
    }

    public Optional<T> minimumNth(int rank) {
        if (outOfBounds(rank)) {
            return Optional.empty();
        }
        BinaryHeap<T> heap = new BinaryHeap<>(HeapOrder.MIN, elements);
        return nth(rank, heap);
    }

    public Optional<T> maximumNth(int rank) {
        if (outOfBounds(rank)) {
            return Optional.empty();
        }
        BinaryHeap<T> heap = new BinaryHeap<>(HeapOrder.MAX, elements);
        return nth(rank, heap);
    }

    public Tuple2<T> median() {
        int middle = elements.length/2;
        if (elements.length % 2 == 1) {
            T min = minimumNth(middle+1).get();
            return new Tuple2<>(min, min);
        }
        return new Tuple2<>(
                minimumNth(middle).get(),
                maximumNth(middle).get()
        );
    }

    private Optional<T> nth(int index, BinaryHeap<T> heap) {
        Optional<T> result = Optional.empty();
        for (int i = 0; i < index; i++) {
            result = heap.pop();
        }
        return result;
    }

    private boolean outOfBounds(int rank) {
        return rank <= 0 || rank > elements.length;
    }
}
