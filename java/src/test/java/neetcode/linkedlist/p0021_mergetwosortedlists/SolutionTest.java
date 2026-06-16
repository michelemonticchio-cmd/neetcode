package neetcode.linkedlist.p0021_mergetwosortedlists;

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
        assertEquals("1->1->2->3->4->5",
            listToString(sol.mergeTwoLists(build(1, 2, 4), build(1, 3, 5))));
    }

    @Test
    void test_both_empty() {
        assertNull(sol.mergeTwoLists(null, null));
    }

    @Test
    void test_first_empty() {
        assertEquals("1->2->3",
            listToString(sol.mergeTwoLists(null, build(1, 2, 3))));
    }

    @Test
    void test_second_empty() {
        assertEquals("1->2->3",
            listToString(sol.mergeTwoLists(build(1, 2, 3), null)));
    }

    @Test
    void test_single_elements_equal() {
        assertEquals("1->1",
            listToString(sol.mergeTwoLists(build(1), build(1))));
    }

    @Test
    void test_all_list1_smaller() {
        assertEquals("1->2->3->4->5->6",
            listToString(sol.mergeTwoLists(build(1, 2, 3), build(4, 5, 6))));
    }

    @Test
    void test_all_list2_smaller() {
        assertEquals("1->2->3->4->5->6",
            listToString(sol.mergeTwoLists(build(4, 5, 6), build(1, 2, 3))));
    }

    @Test
    void test_different_lengths() {
        assertEquals("1->2->3->4->5",
            listToString(sol.mergeTwoLists(build(1, 3, 5), build(2, 4))));
    }

    @Test
    void test_duplicates_across_lists() {
        assertEquals("1->1->2->2->3->3",
            listToString(sol.mergeTwoLists(build(1, 2, 3), build(1, 2, 3))));
    }
}
