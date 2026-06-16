package neetcode.binarysearch.p0004_medianoftwosortedarrays;

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
    void test_odd_total() {
        // [1, 2, 3] → median = 2.0
        assertEquals(2.0, sol.findMedianSortedArrays(new int[]{1, 2}, new int[]{3}));
    }

    @Test
    void test_even_total() {
        // [1, 2, 3, 4] → median = 2.5
        assertEquals(2.5, sol.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }

    @Test
    void test_one_empty_array_odd() {
        assertEquals(2.0, sol.findMedianSortedArrays(new int[]{}, new int[]{1, 2, 3}));
    }

    @Test
    void test_one_empty_array_even() {
        assertEquals(1.5, sol.findMedianSortedArrays(new int[]{}, new int[]{1, 2}));
    }

    @Test
    void test_single_elements() {
        assertEquals(1.5, sol.findMedianSortedArrays(new int[]{1}, new int[]{2}));
    }

    @Test
    void test_all_nums1_smaller() {
        // [1,2] + [3,4,5] = [1,2,3,4,5] → median = 3
        assertEquals(3.0, sol.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4, 5}));
    }

    @Test
    void test_all_nums2_smaller() {
        // [3,4,5] + [1,2] same as above by swap
        assertEquals(3.0, sol.findMedianSortedArrays(new int[]{3, 4, 5}, new int[]{1, 2}));
    }

    @Test
    void test_interleaved() {
        // [1,3] + [2,4] = [1,2,3,4] → median = 2.5
        assertEquals(2.5, sol.findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}));
    }

    @Test
    void test_duplicates() {
        // [1,1] + [1,1] = [1,1,1,1] → median = 1.0
        assertEquals(1.0, sol.findMedianSortedArrays(new int[]{1, 1}, new int[]{1, 1}));
    }

    @Test
    void test_negative_numbers() {
        // [-3,-1] + [-2,0] = [-3,-2,-1,0] → median = -1.5
        assertEquals(-1.5, sol.findMedianSortedArrays(new int[]{-3, -1}, new int[]{-2, 0}));
    }
}
