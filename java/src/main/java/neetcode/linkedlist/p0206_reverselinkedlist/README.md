# 206. Reverse Linked List

🔗 [LeetCode](https://leetcode.com/problems/reverse-linked-list/) | 📺 [NeetCode](https://neetcode.io/problems/reverse-a-linked-list)

**Difficulty:** Easy
**Category:** Linked List

## Problem

Given the head of a singly-linked list, reverse the list in place and
return the new head.

## Linked List recap

A linked list is a chain of nodes where each node holds a value and a
pointer to the next node. Unlike arrays, elements are not contiguous in
memory — you can only reach a node by following `next` pointers from
`head`.

    head
     ↓
    [0] → [1] → [2] → [3] → null

## Approach

**Iterative three-pointer technique.**

To reverse the list, each node's `next` pointer must be redirected to its
predecessor. The challenge: once you overwrite `curr.next`, you lose the
reference to the rest of the list. Solution: save `curr.next` before
overwriting it.

Three pointers maintained at all times:
- `prev` — the already-reversed portion's tail (starts at `null`)
- `curr` — the node currently being processed
- `next` — temporary save of `curr.next` before it's overwritten

Per-step operations:
1. `next = curr.next`   — save the rest of the list
2. `curr.next = prev`   — reverse the link
3. `prev = curr`        — advance prev
4. `curr = next`        — advance curr

When `curr == null`, `prev` points to the new head.

## Trace with [0 → 1 → 2 → 3]

    Initial: prev=null, curr=[0]

    Step 1: next=[1], [0].next=null, prev=[0], curr=[1]
            null ← [0]   [1]→[2]→[3]

    Step 2: next=[2], [1].next=[0], prev=[1], curr=[2]
            null ← [0] ← [1]   [2]→[3]

    Step 3: next=[3], [2].next=[1], prev=[2], curr=[3]
            null ← [0] ← [1] ← [2]   [3]

    Step 4: next=null, [3].next=[2], prev=[3], curr=null
            null ← [0] ← [1] ← [2] ← [3]

    curr==null → return prev=[3] ✅

## Edge cases handled automatically

- **Empty list** (`head == null`): the while loop never runs; returns
  `prev = null`. ✅
- **Single element**: one iteration redirects `next` to `null` (already
  was), returns that node. ✅

No special `if` guards are needed for these cases.

## Complexity

- **Time:** O(n) — each node is visited exactly once
- **Space:** O(1) — three scalar pointers, no auxiliary data structure

## Notes

Reverse Linked List is the foundational operation of the Linked List
category. Variations appear in:

- **Reverse Linked List II** (#92) — reverse only a sub-range `[left, right]`.
- **Palindrome Linked List** (#234) — reverse the second half to compare.
- **Reorder List** (#143) — split, reverse second half, merge.
- **LRU Cache** (#146) — doubly-linked list manipulation.

The three-pointer pattern (`prev`, `curr`, `next`) is the "hello world"
of linked list manipulation and appears in almost every linked list problem
in some form.
