package io.github.fbiville.fdk.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {

    private final T[] elements;
    private final int length;
    private int currentIndex;

    public ArrayIterator(T[] elements) {
        this(elements, elements.length);
    }

    ArrayIterator(T[] elements, int length) {
        this.elements = elements;
        this.length = length;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return elements[currentIndex++];
    }
}
