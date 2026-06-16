package neetcode.linkedlist.p0019_removenthnodefromendoflist;

public class Solution {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Removes the nth node from the end of the list and returns the head.
     *
     * Approach: two pointers with a fixed gap of n nodes.
     * A dummy node before head simplifies the edge case where the head
     * itself is removed (n == list length).
     *
     * 1. Advance fast by n steps from dummy.
     * 2. Advance both slow and fast until fast.next == null.
     * 3. slow is now just before the node to remove: slow.next = slow.next.next.
     *
     * Time:  O(L) — single pass, L = list length
     * Space: O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = dummy;

        // Create a gap of n nodes between slow and fast
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Advance both until fast reaches the last node
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove the node after slow
        slow.next = slow.next.next;

        return dummy.next;
    }
}
