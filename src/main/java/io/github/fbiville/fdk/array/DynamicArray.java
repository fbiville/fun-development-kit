package io.github.fbiville.fdk.array;

import io.github.fbiville.fdk.collection.Countable;
import io.github.fbiville.fdk.collection.Indexable;

import java.util.Iterator;
import java.util.Optional;

import static io.github.fbiville.fdk.array.ArrayOperations.shrink;
import static java.util.Arrays.copyOf;

public class DynamicArray<T> implements Countable, Indexable<T>, Iterable<T> {

    private static final int GROWTH_FACTOR = 2;
    private static final int SHRINK_FACTOR = 4;
    private final int actualSize;
    private final T[] storage;

    @SafeVarargs
    public DynamicArray(T... storage) {
        this.actualSize = storage.length;
        this.storage = copyOf(storage, newSize(storage));
    }

    private DynamicArray(int actualSize, T[] storage) {
        this.actualSize = actualSize;
        this.storage = storage;
    }

    public DynamicArray<T> add(T element) {
        if (isFullAndAtMaximumCapacity()) {
            throw new IllegalStateException("Array has reached maximum capacity");
        }
        int newSize = actualSize + 1;
        if (newSize == storage.length) {
            return new DynamicArray<>(storage).add(element);
        }
        storage[actualSize] = element;
        return new DynamicArray<>(newSize, storage);
    }

    public DynamicArray<T> remove(int index) {
        checkBounds(index);
        int newSize = Math.max(0, actualSize - 1);
        storage[index] = null;
        if (newSize <= storage.length / SHRINK_FACTOR) {
            return new DynamicArray<>(newSize, shrink(storage, GROWTH_FACTOR));
        }
        return new DynamicArray<>(newSize, storage);
    }

    @Override
    public Optional<T> get(int index) {
        checkBounds(index);
        return Optional.ofNullable(storage[index]);
    }

    @Override
    public Optional<Integer> indexOf(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        for (int i = 0; i < actualSize; i++) {
            if (element.equals(storage[i])) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Integer> lastIndexOf(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        for (int i = actualSize - 1; i >= 0; i--) {
            if (element.equals(storage[i])) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<>(storage, actualSize);
    }

    public DynamicArray<T> swap(int index1, int index2) {
        if (index1 == index2) {
            return this;
        }
        T temp = storage[index1];
        storage[index1] = storage[index2];
        storage[index2] = temp;
        return this;
    }

    T[] getInternalStorage() {
        return storage;
    }

    private static <T> int newSize(T[] storage) {
        int length = Math.max(1, storage.length);
        if (length <= Integer.MAX_VALUE / GROWTH_FACTOR) {
            return length * GROWTH_FACTOR;
        }
        return Integer.MAX_VALUE;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= actualSize) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    private boolean isFullAndAtMaximumCapacity() {
        int length = storage.length;
        return length == Integer.MAX_VALUE
                && actualSize == length;
    }
}
