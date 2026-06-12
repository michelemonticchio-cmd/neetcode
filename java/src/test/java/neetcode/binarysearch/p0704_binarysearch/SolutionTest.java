package neetcode.binarysearch.p0704_binarysearch;

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
    void test_found_in_middle() {
        assertEquals(4, sol.search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
    }

    @Test
    void test_not_found() {
        assertEquals(-1, sol.search(new int[]{-1, 0, 3, 5, 9, 12}, 2));
    }

    @Test
    void test_found_at_start() {
        assertEquals(0, sol.search(new int[]{5}, 5));
    }

    @Test
    void test_single_element_not_found() {
        assertEquals(-1, sol.search(new int[]{5}, -5));
    }

    @Test
    void test_found_at_first_index() {
        assertEquals(0, sol.search(new int[]{-1, 0, 3, 5, 9, 12}, -1));
    }

    @Test
    void test_found_at_last_index() {
        assertEquals(5, sol.search(new int[]{-1, 0, 3, 5, 9, 12}, 12));
    }

    @Test
    void test_target_smaller_than_all() {
        assertEquals(-1, sol.search(new int[]{1, 2, 3, 4, 5}, 0));
    }

    @Test
    void test_target_larger_than_all() {
        assertEquals(-1, sol.search(new int[]{1, 2, 3, 4, 5}, 10));
    }

    @Test
    void test_negative_numbers() {
        assertEquals(2, sol.search(new int[]{-10, -5, -1, 0, 3}, -1));
    }

    @Test
    void test_even_length_array() {
        // Tests mid calculation with an even-length array (no exact center)
        assertEquals(3, sol.search(new int[]{1, 2, 3, 4, 5, 6}, 4));
    }
}
