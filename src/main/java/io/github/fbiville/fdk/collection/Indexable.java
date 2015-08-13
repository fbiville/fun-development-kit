package io.github.fbiville.fdk.collection;

import java.util.Optional;

public interface Indexable<T> {

    Optional<T> get(int index);
    Optional<Integer> indexOf(T element);
    Optional<Integer> lastIndexOf(T element);

    default boolean contains(T element) {
        return indexOf(element).isPresent();
    }
}
