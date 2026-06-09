# 155. Min Stack

🔗 [LeetCode](https://leetcode.com/problems/min-stack/) | 📺 [NeetCode](https://neetcode.io/problems/minimum-stack)

**Difficulty:** Medium
**Category:** Stack

## Problem

Design a stack that supports `push`, `pop`, `top`, and `getMin` — all in
**O(1)** time. `getMin` returns the minimum element currently in the stack.

## Approach

**Two parallel stacks.**

A single stack can answer `top` in O(1), but finding the minimum requires
scanning all elements — O(n). The trick is to maintain a second stack,
`minStack`, that records the running minimum at every level.

### Invariant

At any point in time, `minStack.peek()` equals the minimum of all elements
currently in `stack`.

### Operations

**push(val):**
- Push `val` onto `stack`.
- Push `min(val, minStack.peek())` onto `minStack`.
  If `minStack` is empty, push `val` directly.

**pop():**
- Pop from both stacks simultaneously.
- The level below in `minStack` already holds the correct minimum for the
  remaining elements — no recomputation needed.

**top():** return `stack.peek()`

**getMin():** return `minStack.peek()`

### Visualization

    Operation    stack           minStack        note
    push(5)      [5]             [5]             min(5, ∅) = 5
    push(3)      [5, 3]          [5, 3]          min(3, 5) = 3
    push(7)      [5, 3, 7]       [5, 3, 3]       min(7, 3) = 3
    push(2)      [5, 3, 7, 2]    [5, 3, 3, 2]    min(2, 3) = 2
    getMin()  →  2  (minStack top)
    pop()        [5, 3, 7]       [5, 3, 3]       both stacks popped
    getMin()  →  3  (minStack top, 2 is gone)

## Complexity

- **Time:** O(1) for all four operations
- **Space:** O(n) — `minStack` mirrors `stack` in size

## Why not update minStack only when a new minimum appears?

A common optimization idea: only push to `minStack` when `val ≤` current
minimum, and only pop from `minStack` when the popped value equals the
current minimum. This saves space when many pushes don't set a new minimum.

This variant works correctly but requires extra care:

    push(val):  if minStack.isEmpty() or val <= minStack.peek() → push to minStack
    pop():      if stack.peek() == minStack.peek() → also pop from minStack

The simpler "always mirror" approach above avoids the edge cases and is
easier to reason about. Both are O(1) in time; the mirrored version uses
at most 2× the space of the optimized one.

## Notes

Min Stack is a clean example of **augmenting a data structure**: instead of
changing the algorithm, you attach extra bookkeeping to an existing structure
to answer a new query in O(1).

The same idea appears in:
- **Max Stack** (variant) — track the maximum instead of the minimum.
- **Sliding Window Maximum** (#239) — a monotonic deque is essentially a
  generalization of this "running extremum" idea to a moving window.
- Segment trees and sparse tables in competitive programming — they
  precompute range queries so each lookup is O(1) or O(log n).

The mental model: *"If you need O(1) access to some aggregate (min, max,
sum) of the current stack contents, maintain a parallel structure that
tracks that aggregate at every level."*
