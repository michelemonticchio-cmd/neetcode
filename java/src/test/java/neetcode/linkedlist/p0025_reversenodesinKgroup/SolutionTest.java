package neetcode.linkedlist.p0025_reversenodesinKgroup;

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
    void test_k_equals_3_even_groups() {
        // [1,2,3,4,5,6], k=3 → [3,2,1,6,5,4]
        assertEquals("3->2->1->6->5->4",
            listToString(sol.reverseKGroup(build(1, 2, 3, 4, 5, 6), 3)));
    }

    @Test
    void test_k_equals_2_odd_length() {
        // [1,2,3,4,5], k=2 → [2,1,4,3,5]  (5 left as-is)
        assertEquals("2->1->4->3->5",
            listToString(sol.reverseKGroup(build(1, 2, 3, 4, 5), 2)));
    }

    @Test
    void test_k_equals_1() {
        // k=1: no reversal, list unchanged
        assertEquals("1->2->3->4->5",
            listToString(sol.reverseKGroup(build(1, 2, 3, 4, 5), 1)));
    }

    @Test
    void test_k_equals_list_length() {
        // k equals full list length: reverse entire list
        assertEquals("5->4->3->2->1",
            listToString(sol.reverseKGroup(build(1, 2, 3, 4, 5), 5)));
    }

    @Test
    void test_k_greater_than_list_length() {
        // Fewer than k nodes: leave list unchanged
        assertEquals("1->2->3",
            listToString(sol.reverseKGroup(build(1, 2, 3), 4)));
    }

    @Test
    void test_single_node() {
        assertEquals("1",
            listToString(sol.reverseKGroup(build(1), 1)));
    }

    @Test
    void test_k_equals_2_even_length() {
        // [1,2,3,4], k=2 → [2,1,4,3]
        assertEquals("2->1->4->3",
            listToString(sol.reverseKGroup(build(1, 2, 3, 4), 2)));
    }
}
