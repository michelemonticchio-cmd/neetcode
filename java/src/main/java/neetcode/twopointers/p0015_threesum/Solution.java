package neetcode.twopointers.p0015_threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * Returns all unique triplets [a, b, c] in nums such that a + b + c = 0.
     *
     * Approach: sort the array, then for each index i, use converging
     * two pointers (left, right) on the right portion to find pairs that
     * sum to -nums[i]. Skip duplicates at both the outer and inner levels.
     *
     * Time:  O(n^2) — outer loop times inner two-pointers walk
     * Space: O(1) extra (excluding output and the cost of sorting)
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            // With a sorted array, once nums[i] > 0 no further triplet can sum to 0
            if (nums[i] > 0) break;
            // Skip duplicate values at the outer level
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // Skip duplicates on the left side
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // Skip duplicates on the right side
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;     // sum too small → need a bigger left
                } else {
                    right--;    // sum too big → need a smaller right
                }
            }
        }

        return result;
    }
}
