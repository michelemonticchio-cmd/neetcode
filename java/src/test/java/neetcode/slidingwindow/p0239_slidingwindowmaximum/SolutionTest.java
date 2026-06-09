package neetcode.slidingwindow.p0239_slidingwindowmaximum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SolutionTest {

    private Solution sol;

    @BeforeEach
    void setUp() {
        sol = new Solution();
    }

    @Test
    void test_classic_example() {
        assertArrayEquals(
            new int[]{3, 3, 5, 5, 6, 7},
            sol.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)
        );
    }

    @Test
    void test_k_equals_1() {
        // Window of size 1 → output is a copy of input
        assertArrayEquals(
            new int[]{1, 3, -1, -3, 5},
            sol.maxSlidingWindow(new int[]{1, 3, -1, -3, 5}, 1)
        );
    }

    @Test
    void test_k_equals_n() {
        // Window covers the whole array → single maximum
        assertArrayEquals(
            new int[]{5},
            sol.maxSlidingWindow(new int[]{1, 3, -1, -3, 5}, 5)
        );
    }

    @Test
    void test_single_element() {
        assertArrayEquals(
            new int[]{7},
            sol.maxSlidingWindow(new int[]{7}, 1)
        );
    }

    @Test
    void test_all_same() {
        assertArrayEquals(
            new int[]{4, 4, 4},
            sol.maxSlidingWindow(new int[]{4, 4, 4, 4, 4}, 3)
        );
    }

    @Test
    void test_decreasing_array() {
        // Max is always the leftmost element of the window
        assertArrayEquals(
            new int[]{5, 4, 3},
            sol.maxSlidingWindow(new int[]{5, 4, 3, 2, 1}, 3)
        );
    }

    @Test
    void test_increasing_array() {
        // Max is always the rightmost element of the window
        assertArrayEquals(
            new int[]{3, 4, 5},
            sol.maxSlidingWindow(new int[]{1, 2, 3, 4, 5}, 3)
        );
    }

    @Test
    void test_negative_numbers() {
        assertArrayEquals(
            new int[]{-1, -1, -2},
            sol.maxSlidingWindow(new int[]{-1, -3, -1, -2, -4}, 3)
        );
    }

    @Test
    void test_duplicates_at_boundary() {
        // Tests correct eviction when two equal values are at window boundary
        assertArrayEquals(
            new int[]{3, 3, 3},
            sol.maxSlidingWindow(new int[]{3, 1, 3, 1, 3}, 3)
        );
    }
}
