package neetcode.linkedlist.p0023_mergeKsortedlists;

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
    void test_classic_example() {
        Solution.ListNode[] lists = {
            build(1, 4, 5),
            build(1, 3, 4),
            build(2, 6)
        };
        assertEquals("1->1->2->3->4->4->5->6",
            listToString(sol.mergeKLists(lists)));
    }

    @Test
    void test_empty_array() {
        assertNull(sol.mergeKLists(new Solution.ListNode[]{}));
    }

    @Test
    void test_array_with_one_null_list() {
        assertNull(sol.mergeKLists(new Solution.ListNode[]{null}));
    }

    @Test
    void test_single_list() {
        Solution.ListNode[] lists = { build(1, 2, 3) };
        assertEquals("1->2->3", listToString(sol.mergeKLists(lists)));
    }

    @Test
    void test_lists_of_different_lengths() {
        Solution.ListNode[] lists = {
            build(1, 2, 3, 4, 5),
            build(6),
            build(7, 8)
        };
        assertEquals("1->2->3->4->5->6->7->8",
            listToString(sol.mergeKLists(lists)));
    }

    @Test
    void test_some_null_lists() {
        Solution.ListNode[] lists = {
            build(1, 3),
            null,
            build(2, 4)
        };
        assertEquals("1->2->3->4",
            listToString(sol.mergeKLists(lists)));
    }

    @Test
    void test_all_same_values() {
        Solution.ListNode[] lists = {
            build(1, 1),
            build(1, 1),
            build(1, 1)
        };
        assertEquals("1->1->1->1->1->1",
            listToString(sol.mergeKLists(lists)));
    }

    @Test
    void test_two_lists() {
        // Degenerate case: k=2, same as Merge Two Sorted Lists
        Solution.ListNode[] lists = {
            build(1, 3, 5),
            build(2, 4, 6)
        };
        assertEquals("1->2->3->4->5->6",
            listToString(sol.mergeKLists(lists)));
    }
}
