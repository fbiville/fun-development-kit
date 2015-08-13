package io.github.fbiville.fdk.heap;

import io.github.fbiville.fdk.sort.QuickSort;

import java.util.Arrays;

public enum HeapOrder {
    MIN {
        @Override
        public <T extends Comparable<T>> boolean isOrdered(T child, T parent) {
            return child.compareTo(parent) >= 0;
        }
    },
    MAX {
        @Override
        public <T extends Comparable<T>> boolean isOrdered(T child, T parent) {
            return child.compareTo(parent) <= 0;
        }
    };

    public abstract <T extends Comparable<T>> boolean isOrdered(T child, T parent);
}
