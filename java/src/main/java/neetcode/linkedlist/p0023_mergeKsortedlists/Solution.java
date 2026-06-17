package neetcode.linkedlist.p0023_mergeKsortedlists;

import java.util.PriorityQueue;

public class Solution {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Merges k sorted linked lists into one sorted linked list.
     *
     * Approach: min heap (PriorityQueue) of size at most k.
     * Initialize the heap with the head of each non-null list.
     * At each step: extract the minimum node, append it to the result,
     * and insert its successor (if any) into the heap.
     * The heap always holds at most one node per list, so its size never
     * exceeds k.
     *
     * Time:  O(n log k) — n total nodes, each inserted/extracted once
     *        from a heap of size at most k
     * Space: O(k) — the heap holds at most k nodes at any time
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(
            (a, b) -> a.val - b.val   // min heap by node value
        );

        // Seed the heap with the head of every non-null list
        for (ListNode head : lists) {
            if (head != null) heap.offer(head);
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (!heap.isEmpty()) {
            ListNode node = heap.poll();   // extract the current minimum
            curr.next = node;
            curr = curr.next;

            if (node.next != null) {
                heap.offer(node.next);     // enqueue the successor
            }
        }

        return dummy.next;
    }
}
