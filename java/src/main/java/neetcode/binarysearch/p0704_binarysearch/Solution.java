package neetcode.binarysearch.p0704_binarysearch;

public class Solution {
    /**
     * Returns the index of target in a sorted array of distinct integers,
     * or -1 if not present.
     *
     * Approach: classic binary search. Maintain an interval [left, right]
     * that could contain target. Compare target with the middle element
     * and discard the half that cannot contain it.
     *
     * Time:  O(log n)
     * Space: O(1)
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;  // avoids overflow vs (left+right)/2

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;   // target is in the right half
            } else {
                right = mid - 1;  // target is in the left half
            }
        }

        return -1;
    }
}
