package io.github.fbiville.fdk.collection.heap;

import io.github.fbiville.fdk.collection.array.DynamicArray;

import java.util.Iterator;
import java.util.Optional;

import static java.lang.Math.floorDiv;
import static java.util.Optional.empty;

public class BinaryHeap<T extends Comparable<T>> implements Heap<T> {

    private final HeapOrder order;
    private DynamicArray<T> elements;

    @SafeVarargs
    public BinaryHeap(HeapOrder order, T... elements) {
        this.order = order;
        this.elements = initializeHeap(elements);
    }

    @Override
    public void push(T element) {
        elements.add(element);
        rearrangeParent(elements.size());
    }

    @Override
    public Optional<T> pop() {
        if (isEmpty()) {
            return empty();
        }

        Optional<T> top = peek();
        swapRootAndRemove();
        return top;
    }

    @Override
    public Optional<T> peek() {
        if (isEmpty()) {
            return empty();
        }
        return elements.get(0);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    Optional<T> get(int i) {
        if (outOfBounds(i, size())) {
            return Optional.empty();
        }
        return elements.get(i);
    }

    private static Optional<Integer> leftChildIndexOf(int i, int length) {
        return wrapIndex(2 * (1 + i) - 1, length);
    }

    private static Optional<Integer> rightChildIndexOf(int i, int length) {
        return wrapIndex(2 * (1 + i), length);
    }

    private static Optional<Integer> wrapIndex(int result, int length) {
        if (outOfBounds(result, length)) {
            return empty();
        }
        return Optional.of(result);
    }

    private static boolean outOfBounds(int index, int length) {
        return index < 0 || index >= length;
    }

    private final DynamicArray<T> initializeHeap(T[] elements) {
        if (elements.length == 0) {
            return new DynamicArray<>(elements);
        }
        DynamicArray<T> result = new DynamicArray<>(elements);
        for (int i = floorDiv(elements.length, 2); i >= 0; i--) {
            rearrange(result, i, elements.length);
        }
        return result;
    }

    private final void rearrange(DynamicArray<T> array, int rootIndex, int length) {
        if (length == 0) {
            return;
        }
        Optional<Integer> leftChildIndex = leftChildIndexOf(rootIndex, length);
        Optional<Integer> rightChildIndex = rightChildIndexOf(rootIndex, length);
        int indexOfLargest = indexOfLargest(array, rootIndex, leftChildIndex, rightChildIndex);
        if (indexOfLargest != rootIndex) {
            array.swap(indexOfLargest, rootIndex);
            rearrange(array, indexOfLargest, length);
        }
    }

    private void rearrangeParent(int index) {
        Optional<Integer> parent = parentIndexOf(index, size());
        if (!parent.isPresent()) {
            return;
        }
        Integer parentIndex = parent.get();
        if (!order.isOrdered(index, parentIndex)) {
            elements.swap(index, parentIndex);
            rearrangeParent(parentIndex);
        }
    }

    private Optional<Integer> parentIndexOf(int i, int size) {
        return wrapIndex(floorDiv(i, 2), size);
    }

    private int indexOfLargest(DynamicArray<T> array,
                               int rootIndex,
                               Optional<Integer> leftChildIndex,
                               Optional<Integer> rightChildIndex) {

        T max = array.get(rootIndex).get();
        int result = rootIndex;

        if (leftChildIndex.isPresent()) {
            Integer leftIndex = leftChildIndex.get();
            T left = array.get(leftIndex).get();
            if (!order.isOrdered(left, max)) {
                max = left;
                result = leftIndex;
            }
        }
        if (rightChildIndex.isPresent()) {
            Integer rightIndex = rightChildIndex.get();
            T right = array.get(rightIndex).get();
            if (!order.isOrdered(right, max)) {
                result = rightIndex;
            }
        }
        return result;
    }

    private void swapRootAndRemove() {
        int lastIndex = elements.size() - 1;
        elements.swap(0, lastIndex);
        elements = elements.remove(lastIndex);
        rearrange(elements, 0, size());
    }

}
