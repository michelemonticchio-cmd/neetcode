package neetcode.binarysearch.p0153_findmimuminrotatedsortedarray;

public class Solution {
    /**
     * Returns the minimum element of a sorted array that has been rotated
     * an unknown number of times.
     *
     * Approach: binary search exploiting the two-segment structure.
     * A rotated sorted array consists of two ascending segments where the
     * second segment's values are all smaller than the first's. The minimum
     * is always the first element of the second segment (or the only
     * element if the array isn't rotated).
     *
     * For a given mid:
     * - If nums[mid] > nums[right], mid is in the first (high) segment,
     *   so the minimum must be strictly to the right -> left = mid + 1.
     * - Otherwise, mid is in the second (low) segment or is itself the
     *   minimum, so the minimum is at mid or to its left -> right = mid
     *   (mid cannot be excluded).
     *
     * The loop uses left < right (not <=) and converges to left == right
     * pointing exactly at the minimum.
     *
     * Time:  O(log n)
     * Space: O(1)
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;   // minimum is strictly to the right
            } else {
                right = mid;      // mid could be the minimum, keep it in range
            }
        }

        return nums[left];
    }
}
