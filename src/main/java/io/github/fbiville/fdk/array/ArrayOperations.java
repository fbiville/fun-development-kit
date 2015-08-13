package io.github.fbiville.fdk.array;

import java.lang.reflect.Array;
import java.util.Iterator;

public class ArrayOperations {

    public static <T> void swap(T[] array, int first, int second) {
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public static <T> Iterator<T> iterator(T[] elements) {
        return new ArrayIterator<>(elements);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(T[] type, int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Array size must be positive");
        }
        return (T[]) Array.newInstance(type.getClass().getComponentType(), size);
    }

    public static <T> T[] shrink(T[] elements, int factor) {
        if (factor < 1) {
            throw new IllegalArgumentException("Shrink factor should be strictly positive");
        }

        int newSize = elements.length / factor,
            i = 0,
            added = 0;

        T[] result = newArray(elements, newSize);
        while (i < elements.length && added < newSize) {
            T element = elements[i++];
            if (element != null) {
                result[added++] = element;
            }
        }
        return result;
    }
}
