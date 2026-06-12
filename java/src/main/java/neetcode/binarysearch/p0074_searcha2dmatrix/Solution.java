package neetcode.binarysearch.p0074_searcha2dmatrix;

public class Solution {
    /**
     * Returns true if target exists in the matrix.
     * Each row is sorted ascending, and the first element of each row is
     * greater than the last element of the previous row — so the matrix
     * is equivalent to a single sorted array of size rows*cols.
     *
     * Approach: binary search over a "virtual" 1D array of size rows*cols.
     * Each virtual index mid maps to matrix[mid/cols][mid%cols].
     *
     * Time:  O(log(m*n))
     * Space: O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / cols;
            int col = mid % cols;
            int value = matrix[row][col];

            if (value == target) {
                return true;
            } else if (value < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}
