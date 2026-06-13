package neetcode.binarysearch.p0033_searchinrotatedsortedarray;

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
    void test_found_in_right_segment() {
        assertEquals(4, sol.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    }

    @Test
    void test_not_found() {
        assertEquals(-1, sol.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    }

    @Test
    void test_found_in_left_segment() {
        assertEquals(1, sol.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 5));
    }

    @Test
    void test_not_rotated() {
        // Fully sorted array (rotation by n, equivalent to no rotation)
        assertEquals(2, sol.search(new int[]{1, 2, 3, 4, 5, 6, 7}, 3));
    }

    @Test
    void test_single_element_found() {
        assertEquals(0, sol.search(new int[]{5}, 5));
    }

    @Test
    void test_single_element_not_found() {
        assertEquals(-1, sol.search(new int[]{5}, 3));
    }

    @Test
    void test_two_elements() {
        assertEquals(0, sol.search(new int[]{3, 1}, 3));
        assertEquals(1, sol.search(new int[]{3, 1}, 1));
        assertEquals(-1, sol.search(new int[]{3, 1}, 2));
    }

    @Test
    void test_target_at_pivot() {
        // The minimum element (pivot point) itself is the target
        assertEquals(4, sol.search(new int[]{6, 7, 8, 1, 2, 3, 4, 5}, 1));
    }

    @Test
    void test_target_at_first_index() {
        assertEquals(0, sol.search(new int[]{6, 7, 8, 1, 2, 3, 4, 5}, 6));
    }

    @Test
    void test_target_at_last_index() {
        assertEquals(7, sol.search(new int[]{6, 7, 8, 1, 2, 3, 4, 5}, 5));
    }

    @Test
    void test_rotated_by_one() {
        assertEquals(0, sol.search(new int[]{2, 3, 4, 5, 1}, 2));
        assertEquals(4, sol.search(new int[]{2, 3, 4, 5, 1}, 1));
    }
}
