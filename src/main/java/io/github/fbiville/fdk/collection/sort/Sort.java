package io.github.fbiville.fdk.collection.sort;

public interface Sort {

    <T extends Comparable<T>> T[] sort(T[] values);
}
