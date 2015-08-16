package io.github.fbiville.fdk.sort;

public interface Sort {

    <T extends Comparable<T>> T[] sort(T[] values);
}
