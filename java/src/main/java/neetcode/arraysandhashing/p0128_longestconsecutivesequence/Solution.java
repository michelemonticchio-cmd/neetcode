package neetcode.arraysandhashing.p0128_longestconsecutivesequence;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    /**
     * Returns the length of the longest sequence of consecutive integers
     * present in nums (order in the array doesn't matter).
     *
     * Approach: HashSet for O(1) lookups + "start-of-sequence" detection.
     * A number n is the start of a sequence iff (n - 1) is not in the set.
     * From each start we walk forward counting consecutive elements.
     *
     * Time:  O(n) — each number is visited at most twice in total
     * Space: O(n) — the set stores all distinct elements
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        int maxLen = 0;

        for (int n : set) {
            // n is a sequence start only if (n - 1) is not present
            if (!set.contains(n - 1)) {
                int current = n;
                int len = 1;

                while (set.contains(current + 1)) {
                    current++;
                    len++;
                }

                if (len > maxLen) {
                    maxLen = len;
                }
            }
        }

        return maxLen;
    }
}
