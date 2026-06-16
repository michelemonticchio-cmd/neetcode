package neetcode.linkedlist.p0287_findtheduplicatenumber;

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
    void test_example_one() {
        assertEquals(2, sol.findDuplicate(new int[]{1, 3, 4, 2, 2}));
    }

    @Test
    void test_example_two() {
        assertEquals(3, sol.findDuplicate(new int[]{3, 1, 3, 4, 2}));
    }

    @Test
    void test_duplicate_is_one() {
        assertEquals(1, sol.findDuplicate(new int[]{1, 1, 2}));
    }

    @Test
    void test_duplicate_at_start() {
        assertEquals(2, sol.findDuplicate(new int[]{2, 2, 1}));
    }

    @Test
    void test_duplicate_is_largest() {
        assertEquals(4, sol.findDuplicate(new int[]{1, 2, 3, 4, 4}));
    }

    @Test
    void test_minimum_array() {
        // Smallest valid input: n=1, array has 2 elements
        assertEquals(1, sol.findDuplicate(new int[]{1, 1}));
    }

    @Test
    void test_duplicate_appears_many_times() {
        assertEquals(3, sol.findDuplicate(new int[]{3, 3, 3, 3, 3}));
    }

    @Test
    void test_large_range() {
        // n=5, duplicate is 5
        assertEquals(5, sol.findDuplicate(new int[]{5, 1, 4, 3, 2, 5}));
    }
}
