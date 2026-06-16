package neetcode.linkedlist.p0206_reverselinkedlist;

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

    /** Builds a linked list from an array of values. */
    private Solution.ListNode build(int... vals) {
        Solution.ListNode dummy = new Solution.ListNode(0);
        Solution.ListNode cur = dummy;
        for (int v : vals) {
            cur.next = new Solution.ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    /** Converts a linked list to a readable string for assertions. */
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
    void test_multiple_elements() {
        assertEquals("3->2->1->0",
            listToString(sol.reverseList(build(0, 1, 2, 3))));
    }

    @Test
    void test_two_elements() {
        assertEquals("2->1",
            listToString(sol.reverseList(build(1, 2))));
    }

    @Test
    void test_single_element() {
        assertEquals("5",
            listToString(sol.reverseList(build(5))));
    }

    @Test
    void test_empty_list() {
        assertNull(sol.reverseList(null));
    }

    @Test
    void test_already_reversed() {
        // [3,2,1] reversed is [1,2,3]
        assertEquals("1->2->3",
            listToString(sol.reverseList(build(3, 2, 1))));
    }

    @Test
    void test_palindrome_values() {
        // [1,2,1] reversed is [1,2,1]
        assertEquals("1->2->1",
            listToString(sol.reverseList(build(1, 2, 1))));
    }
}
