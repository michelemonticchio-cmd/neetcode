package neetcode.linkedlist.p0141_linkedlistcycle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolutionTest {

    private Solution sol;

    @BeforeEach
    void setUp() {
        sol = new Solution();
    }

    /** Builds a list and optionally connects the tail back to the node at cycleIndex. */
    private Solution.ListNode buildWithCycle(int[] vals, int cycleIndex) {
        if (vals.length == 0) return null;
        Solution.ListNode[] nodes = new Solution.ListNode[vals.length];
        for (int i = 0; i < vals.length; i++) {
            nodes[i] = new Solution.ListNode(vals[i]);
        }
        for (int i = 0; i < vals.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        if (cycleIndex >= 0) {
            nodes[vals.length - 1].next = nodes[cycleIndex];
        }
        return nodes[0];
    }

    @Test
    void test_cycle_at_index_1() {
        // 1→2→3→4→(back to 2)
        assertTrue(sol.hasCycle(buildWithCycle(new int[]{1, 2, 3, 4}, 1)));
    }

    @Test
    void test_cycle_at_index_0() {
        // List loops back to head
        assertTrue(sol.hasCycle(buildWithCycle(new int[]{1, 2}, 0)));
    }

    @Test
    void test_self_loop() {
        // Single node pointing to itself
        assertTrue(sol.hasCycle(buildWithCycle(new int[]{1}, 0)));
    }

    @Test
    void test_no_cycle() {
        // 1→2→3→null
        assertFalse(sol.hasCycle(buildWithCycle(new int[]{1, 2, 3}, -1)));
    }

    @Test
    void test_single_node_no_cycle() {
        assertFalse(sol.hasCycle(buildWithCycle(new int[]{1}, -1)));
    }

    @Test
    void test_empty_list() {
        assertFalse(sol.hasCycle(null));
    }

    @Test
    void test_two_nodes_no_cycle() {
        assertFalse(sol.hasCycle(buildWithCycle(new int[]{1, 2}, -1)));
    }

    @Test
    void test_cycle_at_last_node() {
        // Tail points back to itself
        assertTrue(sol.hasCycle(buildWithCycle(new int[]{1, 2, 3, 4}, 3)));
    }
}
