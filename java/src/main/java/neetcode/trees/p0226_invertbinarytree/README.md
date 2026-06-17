# 226. Invert Binary Tree

🔗 [LeetCode](https://leetcode.com/problems/invert-binary-tree/) | 📺 [NeetCode](https://neetcode.io/problems/invert-a-binary-tree)

**Difficulty:** Easy
**Category:** Trees

## Problem

Given the root of a binary tree, invert it (mirror it) and return its root.

## Binary Tree recap

Unlike arrays, a binary tree is a linked structure of nodes where each node
holds a value and two pointers:

    class TreeNode {
        int val;
        TreeNode left;   // left child (or null)
        TreeNode right;  // right child (or null)
    }

You cannot index into a tree — you must traverse it by following pointers
from the root.

## Approach

**Recursive traversal with child swap.**

To invert a tree rooted at `node`:
1. Recursively invert the right subtree → this becomes the new left child.
2. Recursively invert the left subtree (saved in `temp`) → this becomes the
   new right child.
3. Return `node`.

Base case: if `node == null`, return `null` (nothing to invert).

Because every node is visited exactly once and the operation at each node
is O(1), the total complexity is O(n).

## Complexity

- **Time:** O(n) — every node visited once
- **Space:** O(h) — recursion stack depth = tree height
  - O(log n) for a balanced tree
  - O(n) worst case for a completely skewed tree (essentially a linked list)

## Trace with root = [1, 2, 3, 4, 5, 6, 7]

         1                        1
        / \                      / \
       2   3        →           3   2
      / \ / \                  / \ / \
     4  5 6  7                7  6 5  4

    invertTree(1):
      temp = node(2)
      root.left  = invertTree(3) → swaps 6,7 → returns node(3) with left=7, right=6
      root.right = invertTree(2) → swaps 4,5 → returns node(2) with left=5, right=4
      return node(1) ✅

## Pitfall: save left before overwriting it

The one subtlety: `root.left` is overwritten before `root.right` is set.
If you wrote:

    root.left  = invertTree(root.right);
    root.right = invertTree(root.left);   // ❌ root.left already changed!

you'd invert the same subtree twice and lose the original left. Saving
`root.left` in `temp` first avoids this.

## Notes

Invert Binary Tree is the "hello world" of the Trees category. It
introduces the core pattern used in almost every tree problem:

> *"Solve the problem for the current node, then recurse on left and right."*

This is called **DFS (Depth-First Search)** — specifically pre-order or
post-order traversal depending on whether you act on the node before or
after recurring. Both work here; the solution above is effectively
pre-order (swap happens as we go down).

Subsequent tree problems all use DFS or BFS variants of this skeleton.
