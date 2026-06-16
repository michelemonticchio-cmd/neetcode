package neetcode.linkedlist.p0002_addtwonumbers;

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
    void test_classic_example() {
        // 342 + 465 = 807
        assertEquals("7->0->8",
            listToString(sol.addTwoNumbers(build(2, 4, 3), build(5, 6, 4))));
    }

    @Test
    void test_carry_at_end() {
        // 99 + 1 = 100
        assertEquals("0->0->1",
            listToString(sol.addTwoNumbers(build(9, 9), build(1))));
    }

    @Test
    void test_different_lengths() {
        // 9999 + 1 = 10000
        assertEquals("0->0->0->0->1",
            listToString(sol.addTwoNumbers(build(9, 9, 9, 9), build(1))));
    }

    @Test
    void test_single_digits_no_carry() {
        // 3 + 4 = 7
        assertEquals("7",
            listToString(sol.addTwoNumbers(build(3), build(4))));
    }

    @Test
    void test_single_digits_with_carry() {
        // 5 + 5 = 10
        assertEquals("0->1",
            listToString(sol.addTwoNumbers(build(5), build(5))));
    }

    @Test
    void test_zeros() {
        // 0 + 0 = 0
        assertEquals("0",
            listToString(sol.addTwoNumbers(build(0), build(0))));
    }

    @Test
    void test_multiple_carries() {
        // 999 + 999 = 1998  → [8→9→9→1]
        assertEquals("8->9->9->1",
            listToString(sol.addTwoNumbers(build(9, 9, 9), build(9, 9, 9))));
    }
}
