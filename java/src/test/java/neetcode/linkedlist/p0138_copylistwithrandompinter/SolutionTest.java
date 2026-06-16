package neetcode.linkedlist.p0138_copylistwithrandompinter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution sol;

    @BeforeEach
    void setUp() {
        sol = new Solution();
    }

    @Test
    void test_null_input() {
        assertNull(sol.copyRandomList(null));
    }

    @Test
    void test_single_node_no_random() {
        Solution.Node n1 = new Solution.Node(1);
        Solution.Node copy = sol.copyRandomList(n1);

        assertNotSame(n1, copy);          // different object
        assertEquals(1, copy.val);
        assertNull(copy.next);
        assertNull(copy.random);
    }

    @Test
    void test_single_node_self_random() {
        Solution.Node n1 = new Solution.Node(1);
        n1.random = n1;                   // points to itself

        Solution.Node copy = sol.copyRandomList(n1);

        assertNotSame(n1, copy);
        assertEquals(1, copy.val);
        assertSame(copy, copy.random);    // clone's random points to clone, not original
    }

    @Test
    void test_two_nodes() {
        Solution.Node n1 = new Solution.Node(1);
        Solution.Node n2 = new Solution.Node(2);
        n1.next = n2;
        n1.random = n2;
        n2.random = n1;

        Solution.Node c1 = sol.copyRandomList(n1);
        Solution.Node c2 = c1.next;

        // Values correct
        assertEquals(1, c1.val);
        assertEquals(2, c2.val);

        // No shared references with originals
        assertNotSame(n1, c1);
        assertNotSame(n2, c2);

        // next links correct
        assertSame(c2, c1.next);
        assertNull(c2.next);

        // random links correct and point to clones, not originals
        assertSame(c2, c1.random);
        assertSame(c1, c2.random);
    }

    @Test
    void test_three_nodes_with_nulls() {
        Solution.Node n1 = new Solution.Node(1);
        Solution.Node n2 = new Solution.Node(2);
        Solution.Node n3 = new Solution.Node(3);
        n1.next = n2;
        n2.next = n3;
        n1.random = n3;
        n2.random = null;
        n3.random = n1;

        Solution.Node c1 = sol.copyRandomList(n1);
        Solution.Node c2 = c1.next;
        Solution.Node c3 = c2.next;

        assertNotSame(n1, c1);
        assertNotSame(n2, c2);
        assertNotSame(n3, c3);

        assertSame(c3, c1.random);
        assertNull(c2.random);
        assertSame(c1, c3.random);
    }
}
