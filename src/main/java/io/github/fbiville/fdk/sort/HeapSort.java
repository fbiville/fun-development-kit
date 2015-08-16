package io.github.fbiville.fdk.sort;

import io.github.fbiville.fdk.array.ArrayOperations;
import io.github.fbiville.fdk.heap.BinaryHeap;
import io.github.fbiville.fdk.heap.HeapOrder;

public enum HeapSort implements Sort {

    INSTANCE;

    @Override
    public <T extends Comparable<T>> T[] sort(T[] values) {
        T[] result = ArrayOperations.newArray(values, values.length);
        BinaryHeap<T> heap = new BinaryHeap<>(HeapOrder.MIN, values);
        for (int i = 0; i < values.length; i++) {
            result[i] = heap.pop().get();
        }
        return result;
    }
}
