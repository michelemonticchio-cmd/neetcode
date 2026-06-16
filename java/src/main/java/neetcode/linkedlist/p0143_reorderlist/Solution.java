package neetcode.linkedlist.p0143_reorderlist;

public class Solution {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Reorders a linked list in-place so that nodes alternate between
     * the front and back of the original list:
     * [0,1,2,...,n-1] becomes [0,n-1,1,n-2,2,n-3,...]
     *
     * Approach: three steps.
     * 1. Find the middle with slow/fast pointers and split the list.
     * 2. Reverse the second half (same as #206 Reverse Linked List).
     * 3. Merge the two halves by alternating nodes.
     *
     * Time:  O(n) — three linear passes
     * Space: O(1) — only pointer variables
     */
    public void reorderList(ListNode head) {
        // Step 1: find the middle and split
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode second = slow.next;
        slow.next = null;            // terminate the first half

        // Step 2: reverse the second half
        ListNode prev = null;
        ListNode curr = second;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        second = prev;               // new head of the reversed second half

        // Step 3: merge alternating nodes
        ListNode first = head;
        while (second != null) {
            ListNode next1 = first.next;
            ListNode next2 = second.next;
            first.next = second;
            second.next = next1;
            first = next1;
            second = next2;
        }
    }
}
