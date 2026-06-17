package neetcode.linkedlist.p0025_reversenodesinKgroup;

public class Solution {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
  
    /**
     * Reverses every k consecutive nodes of a linked list in place.
     * If fewer than k nodes remain at the end, they are left as-is.
     *
     * Approach: dummy node + three helpers per group.
     * For each group:
     *   1. getKth() — verify k nodes exist and locate the group tail.
     *   2. Detach the group, reverse it in place.
     *   3. Reconnect: groupPrev → new head (old tail), new tail (old head) → nextGroup.
     *      Advance groupPrev to the new tail for the next iteration.
     *
     * Time:  O(n) — each node is visited at most twice (getKth + reverse)
     * Space: O(1) — only pointer variables
     */
  
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy;

        while (true) {
            // Step 1: check that k nodes remain and locate the group tail
            ListNode kTail = getKth(groupPrev, k);
            if (kTail == null) break;   // fewer than k nodes left → stop

            ListNode kHead = groupPrev.next;
            ListNode nextGroup = kTail.next;

            // Step 2: detach and reverse the group
            kTail.next = null;
            reverse(kHead);

            // Step 3: reconnect
            groupPrev.next = kTail;     // kTail is the new head after reversal
            kHead.next = nextGroup;     // kHead is the new tail after reversal
            groupPrev = kHead;          // advance for the next group
        }

        return dummy.next;
    }

    /**
     * Returns the k-th node after curr, or null if fewer than k nodes exist.
     * Used both to verify group size and to locate the group tail.
     */
  
    private ListNode getKth(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }

    /**
     * Reverses a linked list in place (same as #206 Reverse Linked List).
     * After reversal the original head becomes the tail (next == null because
     * the caller set kTail.next = null before invoking this).
     */
  
    private void reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
    }
}
