package neetcode.binarysearch.p0033_searchinrotatedsortedarray;

public class Solution {
    /**
     * Returns the index of target in a rotated sorted array, or -1 if absent.
     *
     * Approach: binary search with segment identification.
     * Although the whole array isn't sorted, splitting at mid always leaves
     * one half that IS sorted. Determine which half is sorted by comparing
     * nums[mid] with nums[left], then check whether target falls within
     * that sorted half's range. If so, search there; otherwise search the
     * other half.
     *
     * Time:  O(log n)
     * Space: O(1)
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] >= nums[left]) {
                // Left half [left..mid] is sorted
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;   // target in the sorted left half
                } else {
                    left = mid + 1;    // target must be in the right half
                }
            } else {
                // Right half [mid..right] is sorted
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;    // target in the sorted right half
                } else {
                    right = mid - 1;   // target must be in the left half
                }
            }
        }

        return -1;
    }
}
