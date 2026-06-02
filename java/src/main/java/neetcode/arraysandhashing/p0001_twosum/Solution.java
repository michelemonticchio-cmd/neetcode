package neetcode.arraysandhashing.p0001_twosum;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * Returns the indices of the two numbers in nums that sum to target.
     *
     * Time:  O(n)
     * Space: O(n)
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (seen.containsKey(complement)) {
                return new int[] { seen.get(complement), i };
            }

            seen.put(nums[i], i);
        }

        return new int[0];
    }
}
