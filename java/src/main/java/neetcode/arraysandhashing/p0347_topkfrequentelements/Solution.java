package neetcode.arraysandhashing.p0347_topkfrequentelements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    /**
     * Returns the k most frequent elements in nums.
     *
     * Approach: Bucket Sort by frequency.
     * - Count occurrences with a HashMap.
     * - Use an array of buckets where index = frequency.
     * - Scan buckets from highest frequency to lowest, collecting the first k.
     *
     * Time:  O(n) — each step is linear in n
     * Space: O(n) — for the count map and the buckets array
     */
    @SuppressWarnings("unchecked")
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // buckets[f] = list of numbers that appear exactly f times.
        // Max possible frequency is nums.length, so we need nums.length + 1 slots.
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int i = 0; i <= nums.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();
            buckets[freq].add(num);
        }

        // Walk buckets from highest frequency down, collecting until we have k.
        int[] result = new int[k];
        int idx = 0;
        for (int i = buckets.length - 1; i >= 0 && idx < k; i--) {
            for (int num : buckets[i]) {
                result[idx++] = num;
                if (idx == k) break;
            }
        }

        return result;
    }
}
