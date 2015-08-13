package io.github.fbiville.fdk.collection;

public interface Countable {

    int size();
    default boolean isEmpty() {
        return size() == 0;
    }
}
