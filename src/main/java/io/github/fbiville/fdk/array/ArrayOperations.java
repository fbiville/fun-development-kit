package io.github.fbiville.fdk.array;

public class ArrayOperations {

    public static <T> void swap(T[] array, int first, int second) {
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

}
