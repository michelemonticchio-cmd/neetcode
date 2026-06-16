package neetcode.linkedlist.p0002_addtwonumbers;

public class Solution {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Adds two non-negative integers represented as reversed linked lists
     * and returns the sum as a reversed linked list.
     *
     * Approach: simulate digit-by-digit addition with a carry, exactly as
     * done by hand. The reversed storage means the least-significant digit
     * is always at the head — no preprocessing needed.
     *
     * The loop runs while at least one list has remaining digits OR a carry
     * is pending, ensuring a final extra node is created when needed
     * (e.g. 99 + 1 = 100 produces a third node for the leading 1).
     *
     * Time:  O(max(m, n)) — one pass over both lists
     * Space: O(max(m, n)) — the result list has at most max(m,n)+1 nodes
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            int sum   = val1 + val2 + carry;
            carry     = sum / 10;
            int digit = sum % 10;

            curr.next = new ListNode(digit);
            curr = curr.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummy.next;
    }
}
