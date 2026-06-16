package neetcode.linkedlist.p0141_linkedlistcycle;

public class Solution {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Returns true if the linked list contains a cycle.
     *
     * Approach: Floyd's Cycle Detection (tortoise and hare).
     * Two pointers start at head: slow moves one step at a time, fast
     * moves two. If a cycle exists, fast laps slow and they meet inside
     * the cycle. If no cycle exists, fast reaches null.
     *
     * Time:  O(n)
     * Space: O(1) — no auxiliary data structure needed
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;   // pointers met → cycle exists
        }

        return false;   // fast reached null → no cycle
    }
}
