package neetcode.binarysearch.p0074_searcha2dmatrix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolutionTest {

    private Solution sol;

    @BeforeEach
    void setUp() {
        sol = new Solution();
    }

    @Test
    void test_found() {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        assertTrue(sol.searchMatrix(matrix, 3));
    }

    @Test
    void test_not_found() {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        assertFalse(sol.searchMatrix(matrix, 13));
    }

    @Test
    void test_first_element() {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}};
        assertTrue(sol.searchMatrix(matrix, 1));
    }

    @Test
    void test_last_element() {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}};
        assertTrue(sol.searchMatrix(matrix, 20));
    }

    @Test
    void test_single_row() {
        int[][] matrix = {{1, 2, 3, 4, 5}};
        assertTrue(sol.searchMatrix(matrix, 4));
        assertFalse(sol.searchMatrix(matrix, 6));
    }

    @Test
    void test_single_column() {
        int[][] matrix = {{1}, {3}, {5}};
        assertTrue(sol.searchMatrix(matrix, 3));
        assertFalse(sol.searchMatrix(matrix, 2));
    }

    @Test
    void test_single_element_found() {
        int[][] matrix = {{5}};
        assertTrue(sol.searchMatrix(matrix, 5));
    }

    @Test
    void test_single_element_not_found() {
        int[][] matrix = {{5}};
        assertFalse(sol.searchMatrix(matrix, 1));
    }

    @Test
    void test_target_between_rows() {
        // Tests that values "between" row boundaries are correctly rejected
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        assertFalse(sol.searchMatrix(matrix, 8));   // between row 0 and row 1
        assertFalse(sol.searchMatrix(matrix, 21));  // between row 1 and row 2
    }
}
