package neetcode.linkedlist.p0287_findtheduplicatenumber;

public class Solution {
    /**
     * Finds the duplicate number in an array of n+1 integers in [1, n].
     *
     * Approach: Floyd's Cycle Detection on an implicit linked list.
     * Treat each index i as a node and nums[i] as its "next" pointer.
     * Because one value appears twice, two indices point to the same
     * "next" node — creating a cycle. The duplicate is the entry point
     * of that cycle.
     *
     * Phase 1: find a meeting point inside the cycle (slow moves 1 step,
     *          fast moves 2 steps — same as Linked List Cycle #141).
     * Phase 2: reset slow to nums[0] and advance both one step at a time;
     *          they meet at the cycle entry = the duplicate value.
     *
     * Time:  O(n)
     * Space: O(1) — no auxiliary data structure, array is not modified
     */
    public int findDuplicate(int[] nums) {
        // Phase 1: detect the cycle
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Phase 2: find the cycle entry (the duplicate)
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
