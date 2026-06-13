package neetcode.binarysearch.p0875_kokoeatingbananas;

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
    void test_traced_example() {
        assertEquals(4, sol.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
    }

    @Test
    void test_leetcode_example_one() {
        assertEquals(4, sol.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
    }

    @Test
    void test_leetcode_example_two() {
        assertEquals(30, sol.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
    }

    @Test
    void test_leetcode_example_three() {
        assertEquals(23, sol.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));
    }

    @Test
    void test_exactly_enough_hours() {
        // h equals the number of piles → must eat each pile entirely in one hour
        // → k must be at least the largest pile
        assertEquals(11, sol.minEatingSpeed(new int[]{3, 6, 7, 11}, 4));
    }

    @Test
    void test_plenty_of_hours() {
        // Lots of hours → can eat very slowly
        assertEquals(1, sol.minEatingSpeed(new int[]{1, 1, 1, 1}, 100));
    }

    @Test
    void test_single_pile() {
        // One pile of 10, 3 hours → ceil(10/k) <= 3 → k >= ceil(10/3) = 4
        assertEquals(4, sol.minEatingSpeed(new int[]{10}, 3));
    }

    @Test
    void test_single_pile_exact_division() {
        // One pile of 10, 5 hours → k=2 gives exactly 5 hours
        assertEquals(2, sol.minEatingSpeed(new int[]{10}, 5));
    }

    @Test
    void test_all_piles_size_one() {
        // n piles of 1 banana each, h = n hours → k = 1 is enough
        assertEquals(1, sol.minEatingSpeed(new int[]{1, 1, 1, 1, 1}, 5));
    }
}
