package neetcode.arraysandhashing.p0217_containsduplicate;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    /**
     * Returns true if any value appears at least twice in the array,
     * false if every element is distinct.
     *
     * Time:  O(n)
     * Space: O(n)
     */
    public boolean hasDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for (int n : nums) {
            if (seen.contains(n)) {
                return true;
            }
            seen.add(n);
        }

        return false;
    }
}
