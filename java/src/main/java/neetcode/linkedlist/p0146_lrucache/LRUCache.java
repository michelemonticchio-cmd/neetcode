package neetcode.linkedlist.p0146_lrucache;

import java.util.HashMap;
import java.util.Map;

/**
 * Least Recently Used (LRU) Cache.
 *
 * Supports get and put in O(1) average time using a HashMap for O(1)
 * key lookup combined with a doubly linked list to track access order.
 *
 * The list is ordered from least recently used (head side) to most
 * recently used (tail side). Two sentinel nodes (head, tail) eliminate
 * all edge cases for insertion and removal at the boundaries.
 *
 * get(key):  look up the node, move it to the tail, return its value.
 * put(key):  update existing node and move to tail, or insert a new node
 *            at the tail; if over capacity, evict head.next (the LRU node).
 *
 * Time:  O(1) for both get and put
 * Space: O(capacity)
 */
public class LRUCache {

    private class Node {
        int key, val;
        Node prev, next;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head;   // sentinel — LRU side
    private final Node tail;   // sentinel — MRU side

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        moveToTail(node);       // mark as most recently used
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            moveToTail(node);   // update value and mark as most recently used
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            insertAtTail(node);
            if (map.size() > capacity) {
                Node lru = head.next;   // least recently used node
                remove(lru);
                map.remove(lru.key);
            }
        }
    }

    /** Removes a node from the doubly linked list in O(1). */
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /** Inserts a node immediately before the tail sentinel (MRU position). */
    private void insertAtTail(Node node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }

    /** Moves an existing node to the MRU position. */
    private void moveToTail(Node node) {
        remove(node);
        insertAtTail(node);
    }
}
