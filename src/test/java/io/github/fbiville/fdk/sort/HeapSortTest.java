package io.github.fbiville.fdk.sort;

public class HeapSortTest extends SortTestCases {

    @Override
    protected <T extends Comparable<T>> T[] sort(T[] values) {
        return HeapSort.INSTANCE.sort(values);
    }
}