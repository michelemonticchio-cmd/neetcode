package neetcode.stack.p0739_dailytemperatures;

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
            new int[]{1, 1, 4, 2, 1, 1, 0, 0},
            sol.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})
        );
    }

    @Test
    void test_all_decreasing() {
        // No warmer day ever → all zeros
        assertArrayEquals(
            new int[]{0, 0, 0, 0},
            sol.dailyTemperatures(new int[]{76, 75, 74, 73})
        );
    }

    @Test
    void test_all_increasing() {
        // Every day has a warmer tomorrow
        assertArrayEquals(
            new int[]{1, 1, 1, 0},
            sol.dailyTemperatures(new int[]{70, 71, 72, 73})
        );
    }

    @Test
    void test_all_equal() {
        // Equal temperature is not warmer → all zeros
        assertArrayEquals(
            new int[]{0, 0, 0, 0},
            sol.dailyTemperatures(new int[]{70, 70, 70, 70})
        );
    }

    @Test
    void test_single_element() {
        assertArrayEquals(
            new int[]{0},
            sol.dailyTemperatures(new int[]{50})
        );
    }

    @Test
    void test_two_elements_warmer() {
        assertArrayEquals(
            new int[]{1, 0},
            sol.dailyTemperatures(new int[]{50, 60})
        );
    }

    @Test
    void test_two_elements_not_warmer() {
        assertArrayEquals(
            new int[]{0, 0},
            sol.dailyTemperatures(new int[]{60, 50})
        );
    }

    @Test
    void test_warmer_day_far_away() {
        // Only the last day is warmer than everything before it
        assertArrayEquals(
            new int[]{4, 3, 2, 1, 0},
            sol.dailyTemperatures(new int[]{50, 50, 50, 50, 100})
        );
    }
}
