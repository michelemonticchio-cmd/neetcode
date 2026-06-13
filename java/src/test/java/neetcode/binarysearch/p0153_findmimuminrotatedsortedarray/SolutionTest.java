package neetcode.binarysearch.p0153_findmimuminrotatedsortedarray;

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
    void test_rotated_example() {
        assertEquals(0, sol.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
    }

    @Test
    void test_rotated_once() {
        assertEquals(1, sol.findMin(new int[]{3, 1, 2}));
    }

    @Test
    void test_not_rotated() {
        // No rotation: minimum is the first element
        assertEquals(1, sol.findMin(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void test_rotated_by_one() {
        assertEquals(1, sol.findMin(new int[]{2, 3, 4, 5, 1}));
    }

    @Test
    void test_single_element() {
        assertEquals(5, sol.findMin(new int[]{5}));
    }

    @Test
    void test_two_elements_rotated() {
        assertEquals(1, sol.findMin(new int[]{2, 1}));
    }

    @Test
    void test_two_elements_not_rotated() {
        assertEquals(1, sol.findMin(new int[]{1, 2}));
    }

    @Test
    void test_minimum_at_start() {
        // Already in "rotated by n" form — equivalent to not rotated
        assertEquals(0, sol.findMin(new int[]{0, 1, 2, 4, 5, 6, 7}));
    }

    @Test
    void test_large_rotation() {
        // [11,13,15,17] rotated to put minimum near the end
        assertEquals(11, sol.findMin(new int[]{15, 17, 11, 13}));
    }

    @Test
    void test_minimum_second_to_last() {
        assertEquals(0, sol.findMin(new int[]{3, 4, 5, 0, 1, 2}));
    }
}
