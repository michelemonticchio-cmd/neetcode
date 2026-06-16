package neetcode.linkedlist.p0206_reverselinkedlist;

public class Solution {

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Reverses a singly-linked list in place and returns the new head.
     *
     * Approach: iterative three-pointer technique.
     * At each step, before overwriting curr.next, save it in a temporary
     * variable. Then redirect curr.next to prev (reversing the link),
     * and advance both pointers one step forward.
     *
     * Time:  O(n) — one pass through the list
     * Space: O(1) — only three pointer variables
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;  // save next before overwriting
            curr.next = prev;           // reverse the link
            prev = curr;                // advance prev
            curr = next;                // advance curr
        }

        return prev;   // prev is the new head
    }
}
