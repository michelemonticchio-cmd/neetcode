package neetcode.stack.p0084_largestrectangleinhistogram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    private Solution sol;

    @BeforeEach
    void setUp() {
        sol = new Solution();
    }

    @Test
    void test_classic_example() {
        // Largest rectangle: bars 2 and 3 (heights 5 and 6), area = 5*2 = 10
        assertEquals(10, sol.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }

    @Test
    void test_two_bars() {
        assertEquals(4, sol.largestRectangleArea(new int[]{2, 4}));
    }

    @Test
    void test_single_bar() {
        assertEquals(5, sol.largestRectangleArea(new int[]{5}));
    }

    @Test
    void test_all_same_height() {
        // Rectangle spans all bars
        assertEquals(12, sol.largestRectangleArea(new int[]{3, 3, 3, 3}));
    }

    @Test
    void test_increasing() {
        // Largest rectangle: full bar 3 (height 3, width 1 = 3)
        // or bars 1-3 at height 1 (width 3 = 3) — both give 3
        // Actually bar 2+3 at height 2 = 4, bar 3 alone = 3 → max = 4
        assertEquals(4, sol.largestRectangleArea(new int[]{1, 2, 3, 4}));
    }

    @Test
    void test_decreasing() {
        // Mirror of increasing — same answer by symmetry
        assertEquals(4, sol.largestRectangleArea(new int[]{4, 3, 2, 1}));
    }

    @Test
    void test_valley() {
        // [5, 1, 5] → tallest single bars give 5, wide rectangle at 1 gives 3
        assertEquals(5, sol.largestRectangleArea(new int[]{5, 1, 5}));
    }

    @Test
    void test_mountain() {
        // [1, 5, 1] → middle bar alone = 5, all three at 1 = 3
        assertEquals(5, sol.largestRectangleArea(new int[]{1, 5, 1}));
    }

    @Test
    void test_tall_single_bar() {
        // [1, 1, 1, 1, 100] → 100×1=100 vs 1×5=5
        assertEquals(100, sol.largestRectangleArea(new int[]{1, 1, 1, 1, 100}));
    }

    @Test
    void test_all_ones() {
        assertEquals(5, sol.largestRectangleArea(new int[]{1, 1, 1, 1, 1}));
    }
}
