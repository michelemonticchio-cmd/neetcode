package neetcode.trees.p0226_invertbinarytree;

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
    void test_full_tree() {
        //      1              1
        //     / \    →       / \
        //    2   3          3   2
        //   / \ / \        / \ / \
        //  4  5 6  7      7  6 5  4
        Solution.TreeNode root = new Solution.TreeNode(1,
            new Solution.TreeNode(2,
                new Solution.TreeNode(4), new Solution.TreeNode(5)),
            new Solution.TreeNode(3,
                new Solution.TreeNode(6), new Solution.TreeNode(7)));

        Solution.TreeNode result = sol.invertTree(root);

        assertEquals(1, result.val);
        assertEquals(3, result.left.val);
        assertEquals(2, result.right.val);
        assertEquals(7, result.left.left.val);
        assertEquals(6, result.left.right.val);
        assertEquals(5, result.right.left.val);
        assertEquals(4, result.right.right.val);
    }

    @Test
    void test_single_node() {
        Solution.TreeNode root = new Solution.TreeNode(1);
        Solution.TreeNode result = sol.invertTree(root);
        assertEquals(1, result.val);
        assertNull(result.left);
        assertNull(result.right);
    }

    @Test
    void test_null_root() {
        assertNull(sol.invertTree(null));
    }

    @Test
    void test_two_nodes_left_only() {
        //   1        1
        //  /    →     \
        // 2             2
        Solution.TreeNode root = new Solution.TreeNode(1,
            new Solution.TreeNode(2), null);
        Solution.TreeNode result = sol.invertTree(root);
        assertNull(result.left);
        assertEquals(2, result.right.val);
    }

    @Test
    void test_two_nodes_right_only() {
        //   1          1
        //    \    →   /
        //     2      2
        Solution.TreeNode root = new Solution.TreeNode(1,
            null, new Solution.TreeNode(2));
        Solution.TreeNode result = sol.invertTree(root);
        assertEquals(2, result.left.val);
        assertNull(result.right);
    }

    @Test
    void test_inverting_twice_gives_original() {
        Solution.TreeNode root = new Solution.TreeNode(1,
            new Solution.TreeNode(2,
                new Solution.TreeNode(4), new Solution.TreeNode(5)),
            new Solution.TreeNode(3));

        sol.invertTree(sol.invertTree(root));

        assertEquals(2, root.left.val);
        assertEquals(3, root.right.val);
        assertEquals(4, root.left.left.val);
        assertEquals(5, root.left.right.val);
    }
}
