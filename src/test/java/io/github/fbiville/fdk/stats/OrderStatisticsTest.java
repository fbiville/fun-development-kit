package io.github.fbiville.fdk.stats;

import io.github.fbiville.fdk.collection.Pair;
import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class OrderStatisticsTest {

    @Test
    public void empty_result_for_rank_out_of_bounds() throws Exception {
        OrderStatistics<Integer> statistics = new OrderStatistics<>(1, 2);
        assertThat(statistics.minimumNth(0)).isEmpty();
        assertThat(statistics.maximumNth(3)).isEmpty();
    }

    @Test
    public void computes_nth_minimum() {
        OrderStatistics<Integer> statistics = new OrderStatistics<>(1, 2, 3, 4);

        assertThat(statistics.minimumNth(2))
                .isPresent()
                .contains(2);
    }

    @Test
    public void computes_nth_maximum() {
        OrderStatistics<Integer> statistics = new OrderStatistics<>(1, 2, 3, 4);

        assertThat(statistics.maximumNth(2))
                .isPresent()
                .contains(3);
    }

    @Test
    public void computes_median_for_even_set() {
        OrderStatistics<String> strings = new OrderStatistics<>("a", "b", "c", "d");

        assertThat(strings.median())
                .isEqualTo(new Pair<>("b", "c"));
    }

    @Test
    public void computes_median_for_odd_set() {
        OrderStatistics<Integer> ints = new OrderStatistics<>(1, 2, 3, 4, 5);

        assertThat(ints.median())
                .isEqualTo(new Pair<>(3, 3));
    }
}