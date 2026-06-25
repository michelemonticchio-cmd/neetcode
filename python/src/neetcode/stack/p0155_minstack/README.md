# 155. Min Stack — Python

🔗 [LeetCode](https://leetcode.com/problems/min-stack/) | 📺 [NeetCode](https://neetcode.io/problems/minimum-stack)

**Difficulty:** Medium
**Category:** Stack

## Problem

Design a stack supporting `push`, `pop`, `top` and `getMin` — all in O(1).

## Approach

**Two parallel lists.**

- `stack` — holds all values.
- `minStack` — holds the running minimum at every level.

On `push`, the new minimum is `min(val, minStack[-1])`.
On `pop`, both lists are popped together.
`getMin()` always returns `minStack[-1]`.

## Python specifics

    minVal = min(val, self.minStack[-1] if self.minStack else val)

The conditional expression handles the empty case inline — no separate
`if not self.minStack` block needed.

## Complexity

- **Time:** O(1) for all operations
- **Space:** O(n)

## Java vs Python

    // Java
    private Stack<Integer> stack = new Stack<>();
    stack.push(val);
    stack.peek();
    stack.pop();

    # Python
    self.stack = []
    self.stack.append(val)
    self.stack[-1]    # peek
    self.stack.pop()
