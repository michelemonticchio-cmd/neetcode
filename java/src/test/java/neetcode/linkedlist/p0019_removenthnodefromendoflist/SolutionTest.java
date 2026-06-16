package neetcode.linkedlist.p0019_removenthnodefromendoflist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    void test_remove_second_from_end() {
        // [1,2,3,4,5], n=2 → [1,2,3,5]
        assertEquals("1->2->3->5",
            listToString(sol.removeNthFromEnd(build(1, 2, 3, 4, 5), 2)));
    }

    @Test
    void test_remove_only_node() {
        // [5], n=1 → []
        assertNull(sol.removeNthFromEnd(build(5), 1));
    }

    @Test
    void test_remove_head() {
        // [1,2], n=2 → [2]  (remove the head)
        assertEquals("2",
            listToString(sol.removeNthFromEnd(build(1, 2), 2)));
    }

    @Test
    void test_remove_tail() {
        // [1,2,3], n=1 → [1,2]
        assertEquals("1->2",
            listToString(sol.removeNthFromEnd(build(1, 2, 3), 1)));
    }

    @Test
    void test_remove_middle() {
        // [1,2,3,4,5], n=3 → [1,2,4,5]
        assertEquals("1->2->4->5",
            listToString(sol.removeNthFromEnd(build(1, 2, 3, 4, 5), 3)));
    }

    @Test
    void test_two_nodes_remove_last() {
        // [1,2], n=1 → [1]
        assertEquals("1",
            listToString(sol.removeNthFromEnd(build(1, 2), 1)));
    }

    @Test
    void test_remove_first_from_end_long_list() {
        // [1,2,3,4,5], n=1 → [1,2,3,4]
        assertEquals("1->2->3->4",
            listToString(sol.removeNthFromEnd(build(1, 2, 3, 4, 5), 1)));
    }
}
