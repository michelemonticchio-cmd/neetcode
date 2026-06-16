package neetcode.linkedlist.p0138_copylistwithrandompinter;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static class Node {
        int val;
        Node next;
        Node random;
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * Returns a deep copy of a linked list where each node has a val,
     * a next pointer, and a random pointer to any node in the list (or null).
     *
     * Approach: two-pass with a HashMap.
     * Pass 1: create a clone for every original node (values only).
     * Pass 2: wire up next and random on each clone using the map.
     *
     * map.get(null) returns null in Java, so null next/random pointers
     * are handled automatically with no special-casing.
     *
     * Time:  O(n) — two linear passes
     * Space: O(n) — one map entry per node
     */
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<>();

        // Pass 1: create all clones (values only, no links yet)
        Node curr = head;
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        // Pass 2: wire next and random using the map
        curr = head;
        while (curr != null) {
            map.get(curr).next   = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }

        return map.get(head);
    }
}
