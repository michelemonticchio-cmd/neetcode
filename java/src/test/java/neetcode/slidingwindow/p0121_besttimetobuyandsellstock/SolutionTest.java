package neetcode.slidingwindow.p0121_besttimetobuyandsellstock;

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
    void test_neetcode_example() {
        // Buy at 1 (day 1), sell at 7 (day 4), profit = 6
        assertEquals(6, sol.maxProfit(new int[]{10, 1, 5, 6, 7, 1}));
    }

    @Test
    void test_leetcode_example() {
        // [7,1,5,3,6,4] → buy at 1, sell at 6, profit = 5
        assertEquals(5, sol.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    @Test
    void test_monotonically_decreasing() {
        // No profitable trade possible → 0
        assertEquals(0, sol.maxProfit(new int[]{10, 7, 5, 2}));
    }

    @Test
    void test_monotonically_increasing() {
        // Buy at first day, sell at last day → 4
        assertEquals(4, sol.maxProfit(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void test_single_element() {
        // Cannot perform a transaction with only one day
        assertEquals(0, sol.maxProfit(new int[]{5}));
    }

    @Test
    void test_two_elements_profitable() {
        assertEquals(3, sol.maxProfit(new int[]{2, 5}));
    }

    @Test
    void test_two_elements_unprofitable() {
        assertEquals(0, sol.maxProfit(new int[]{5, 2}));
    }

    @Test
    void test_all_same_price() {
        // Flat prices → no profit
        assertEquals(0, sol.maxProfit(new int[]{4, 4, 4, 4}));
    }

    @Test
    void test_minimum_at_end() {
        // [3, 2, 1] — the minimum is at the last day, can't sell after it
        assertEquals(0, sol.maxProfit(new int[]{3, 2, 1}));
    }

    @Test
    void test_profit_just_before_minimum() {
        // [6, 1, 3, 2, 4, 7] — buy at 1, sell at 7, profit = 6
        // Tests that the running minimum stays at 1 even after dips
        assertEquals(6, sol.maxProfit(new int[]{6, 1, 3, 2, 4, 7}));
    }
}
