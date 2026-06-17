package neetcode.trees.p0226_invertbinarytree;

public class Solution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Inverts a binary tree in place and returns its root.
     *
     * Approach: recursive post-order (or pre-order) traversal.
     * At each node, recursively invert the right and left subtrees,
     * then assign them to left and right respectively (swapping them).
     * The recursion reaches every node exactly once.
     *
     * Time:  O(n) — every node is visited once
     * Space: O(h) — recursion stack depth equals tree height h
     *        O(log n) for balanced trees, O(n) worst case (skewed tree)
     */
  
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode temp = root.left;
        root.left  = invertTree(root.right);  // invert right subtree → assign to left
        root.right = invertTree(temp);         // invert left subtree  → assign to right

        return root;
    }
}
