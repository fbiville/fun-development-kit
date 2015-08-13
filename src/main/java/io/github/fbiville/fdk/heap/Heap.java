package io.github.fbiville.fdk.heap;

import io.github.fbiville.fdk.collection.Countable;

import java.util.Optional;

public interface Heap<T extends Comparable<T>> extends Iterable<T>, Countable {

    Optional<T> peek();
    void push(T element);
    Optional<T> pop();

    default int maxWidth() {
        int width = 1;
        while (width <= size()) {
            width *= 2;
        }
        return width - 1;
    }
}
