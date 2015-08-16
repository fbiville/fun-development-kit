package io.github.fbiville.fdk.collection;

import java.util.Objects;

public class Pair<T> {

    private final T left;
    private final T right;

    public Pair(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public T getRight() {
        return right;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        return Objects.equals(this.left, other.left)
                && Objects.equals(this.right, other.right);
    }

    @Override
    public String toString() {
        return String.format("{%s,%s}", left, right);
    }
}
