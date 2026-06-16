package neetcode.linkedlist.p0143_reorderlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    private Solution sol;

    @BeforeEach
    void setUp() {
        sol = new Solution();
    }

    private Solution.ListNode build(int... vals) {
        Solution.ListNode dummy = new Solution.ListNode(0);
        Solution.ListNode cur = dummy;
        for (int v : vals) {
            cur.next = new Solution.ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    private String listToString(Solution.ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append("->");
            head = head.next;
        }
        return sb.toString();
    }

    @Test
    void test_odd_length() {
        // [0,1,2,3,4] → [0,4,1,3,2]
        Solution.ListNode head = build(0, 1, 2, 3, 4);
        sol.reorderList(head);
        assertEquals("0->4->1->3->2", listToString(head));
    }

    @Test
    void test_even_length() {
        // [0,1,2,3,4,5,6] → [0,6,1,5,2,4,3]
        Solution.ListNode head = build(0, 1, 2, 3, 4, 5, 6);
        sol.reorderList(head);
        assertEquals("0->6->1->5->2->4->3", listToString(head));
    }

    @Test
    void test_two_elements() {
        // [1,2] → [1,2] (no change)
        Solution.ListNode head = build(1, 2);
        sol.reorderList(head);
        assertEquals("1->2", listToString(head));
    }

    @Test
    void test_three_elements() {
        // [1,2,3] → [1,3,2]
        Solution.ListNode head = build(1, 2, 3);
        sol.reorderList(head);
        assertEquals("1->3->2", listToString(head));
    }

    @Test
    void test_four_elements() {
        // [1,2,3,4] → [1,4,2,3]
        Solution.ListNode head = build(1, 2, 3, 4);
        sol.reorderList(head);
        assertEquals("1->4->2->3", listToString(head));
    }

    @Test
    void test_single_element() {
        // [1] → [1] (no change)
        Solution.ListNode head = build(1);
        sol.reorderList(head);
        assertEquals("1", listToString(head));
    }
}
