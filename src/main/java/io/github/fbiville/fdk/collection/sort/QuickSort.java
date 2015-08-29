package io.github.fbiville.fdk.collection.sort;

import static io.github.fbiville.fdk.collection.array.ArrayOperations.swap;

public enum QuickSort implements Sort {

    INSTANCE;

    public <T extends Comparable<T>> T[] sort(T[] values) {
        sort(values, 0, values.length);
        return values;
    }

    private static <T extends Comparable<T>> void sort(T[] values, int start, int end) {
        if ((end - start) < 2) {
            return;
        }

        if (end - start  == 2) {
            if (values[start].compareTo(values[start+1]) > 0) {
                swap(values, start, start+1);
            }
            return;
        }

        int pivotIndex = indexOfPivot(values, start, end);
        swapPivot(values, start, pivotIndex);

        int newPivotPosition = partitionAroundPivot(values, start, end);

        sort(values, start, newPivotPosition);
        sort(values, newPivotPosition+1, end);
    }


    private static <T extends Comparable<T>> int indexOfPivot(T[] values, int start, int end) {
        T first = values[start];
        T last = values[end - 1];
        T middle = values[start + (end - start) / 2];

        T median = median(first, last, middle);
        if (first.compareTo(median) == 0) {
            return start;
        }
        if (last.compareTo(median) == 0) {
            return end - 1;
        }
        return start + (end - start) / 2;
    }

    private static <T extends Comparable<T>> void swapPivot(T[] values, int start, int position) {
        if (position == start) {
            return;
        }
        swap(values, start, position);
    }

    private static <T extends Comparable<T>> int partitionAroundPivot(T[] values, int start, int end) {
        int currentBoundary = start;
        T pivot = values[start];
        for (int i = start + 1; i < end; i++) {
            T current = values[i];
            if (current.compareTo(pivot) <= 0) {
                swap(values, i, ++currentBoundary);
            }
        }
        swap(values, start, currentBoundary);
        return currentBoundary;
    }

    private static <T extends Comparable<T>> T median(T val1, T val2, T val3) {
        if (val1.compareTo(val2) <= 0 && val2.compareTo(val3) <= 0) {
            return val2;
        }
        if (val2.compareTo(val1) <= 0 && val1.compareTo(val3) <= 0) {
            return val1;
        }
        return val3;
    }
}
