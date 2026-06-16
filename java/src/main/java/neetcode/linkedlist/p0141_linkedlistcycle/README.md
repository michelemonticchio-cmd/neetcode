# 141. Linked List Cycle

🔗 [LeetCode](https://leetcode.com/problems/linked-list-cycle/) | 📺 [NeetCode](https://neetcode.io/problems/linked-list-cycle-detection)

**Difficulty:** Easy
**Category:** Linked List

## Problem

Given the head of a linked list, return `true` if the list contains a cycle
(i.e. some node can be reached again by following `next` pointers), or
`false` otherwise.

## Approach

**Floyd's Cycle Detection — tortoise and hare.**

Two pointers start at `head`:
- `slow` advances **one** node per step.
- `fast` advances **two** nodes per step.

If a cycle exists, `fast` will eventually lap `slow` inside the cycle and
they will point to the same node. If no cycle exists, `fast` reaches `null`.

### Why they always meet inside a cycle

Once both pointers are inside the cycle, consider the distance between them.
Each step, `fast` closes that gap by one (it moves 2, slow moves 1 → net
gain of 1). Since the cycle has finite length, the gap eventually reaches 0
and they meet.

### Guard condition `fast != null && fast.next != null`

`fast.next.next` is only safe when both `fast` and `fast.next` are non-null.
Checking `fast != null` alone would throw a `NullPointerException` on the
second dereference.

## Complexity

- **Time:** O(n) — in the worst case both pointers traverse the whole list
- **Space:** O(1) — only two pointer variables

## Comparison with the HashSet approach

An alternative stores visited nodes in a `HashSet<ListNode>` and returns
`true` the first time a node is seen twice. This is O(n) time and O(n)
space — correct, but Floyd's algorithm achieves the same in O(1) space and
is the expected answer in interviews.

Note: always store **node references**, never `node.val`. Two distinct nodes
can have the same value without forming a cycle.

## Trace with 1→2→3→4→(back to 2)

    slow=1, fast=1
    Step 1: slow=2, fast=3
    Step 2: slow=3, fast=2   (fast wrapped around the cycle)
    Step 3: slow=4, fast=4   → slow==fast → return true ✅

## Trace with 1→2→3→null (no cycle)

    slow=1, fast=1
    Step 1: slow=2, fast=3
    Step 2: slow=3, fast=null → fast==null → exit loop → return false ✅

## Notes

Floyd's algorithm is a classic that appears in two forms:

- **Cycle detection** (this problem): do the pointers ever meet?
- **Cycle entry point** (#142 Linked List Cycle II): where exactly do they
  meet, and what does that tell us about where the cycle starts?

The same two-pointer "speed difference" idea generalizes to:
- **Middle of the Linked List** (#876) — when fast reaches the end, slow
  is at the middle.
- **Reorder List** (#143) — uses the middle-finding variant as its first step.
