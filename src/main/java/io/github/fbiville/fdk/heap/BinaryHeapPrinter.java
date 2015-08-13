package io.github.fbiville.fdk.heap;

import java.util.Optional;
import java.util.function.Function;

public class BinaryHeapPrinter<T extends Comparable<T>> {

    private static final double LN_2 = Math.log(2);
    private final String separator;
    private final Function<T, String> printFunction;

    public BinaryHeapPrinter() {
        this(T::toString);
    }

    public BinaryHeapPrinter(Function<T, String> printFunction) {
        this(printFunction, "_");
    }

    public BinaryHeapPrinter(Function<T, String> printFunction, String separator) {
        this.printFunction = printFunction;
        this.separator = separator;
    }

    public String print(BinaryHeap<T> heap) {
        if (heap.isEmpty()) {
            return "";
        }

        int maxWidth = heap.maxWidth();
        StringBuilder result = new StringBuilder();
        for (int level = 1; level <= maxWidth; level*=2) {
            int nodeCount = nodeCountAtLevel(level);
            int space = 2*nodeCount-1;
            int leftMargin = (maxWidth-space)/2;
            int rightMargin = maxWidth-leftMargin-space;
            printLeftMargin(result, leftMargin);
            printElements(result, heap, level, nodeCount);
            printRightMargin(result, rightMargin);
            result.append(System.lineSeparator());
        }
        return result.toString();
    }

    private void printLeftMargin(StringBuilder result, int marginLeft) {
        for (int i = 0; i < marginLeft; i++) {
            appendSeparator(result);
        }
    }

    private void printElements(StringBuilder result, BinaryHeap<T> heap, int level, int nodeCount) {
        int last = level - 2 + nodeCount;
        int i = level - 1;
        while (i < last) {
            appendElementOrSeparator(result, heap.get(i));
            appendSeparator(result);
            i++;
        }
        appendElementOrSeparator(result, heap.get(last));
    }

    private void printRightMargin(StringBuilder result, int marginRight) {
        for (int i = 0; i < marginRight; i++) {
            appendSeparator(result);
        }
    }

    private void appendElementOrSeparator(StringBuilder result, Optional<T> element) {
        result.append(element.map(printFunction).orElse(separator));
    }

    private void appendSeparator(StringBuilder result) {
        result.append(separator);
    }

    private int nodeCountAtLevel(int level) {
        return 1+((int)Math.floor(Math.log(level)/LN_2));
    }
}
