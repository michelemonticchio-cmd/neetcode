package neetcode.linkedlist.p0021_mergetwosortedlists;

public class Solution {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Merges two sorted linked lists into one sorted linked list.
     *
     * Approach: dummy head + iterative merge.
     * A dummy node avoids special-casing the first node of the result.
     * At each step, the smaller of the two current heads is appended to
     * the result and its pointer is advanced. When one list is exhausted,
     * the remainder of the other is appended directly (it's already sorted).
     *
     * Time:  O(m + n) — each node is visited once
     * Space: O(1) — existing nodes are reused; only the dummy is new
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        // Attach the remaining nodes (at most one list is non-null)
        curr.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }
}
