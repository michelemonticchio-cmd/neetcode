# 287. Find the Duplicate Number

🔗 [LeetCode](https://leetcode.com/problems/find-the-duplicate-number/) | 📺 [NeetCode](https://neetcode.io/problems/find-duplicate-integer)

**Difficulty:** Medium
**Category:** Linked List

## Problem

Given an array `nums` of `n + 1` integers where each value is in `[1, n]`,
exactly one value is repeated. Find that duplicate without modifying the
array and using only O(1) extra space.

## Approach

**Floyd's Cycle Detection on an implicit linked list.**

### The implicit linked list

Treat each index `i` as a node and `nums[i]` as its `next` pointer:

    index:  0  1  2  3  4
    nums:   1  3  4  2  2

    node 0 → node 1 → node 3 → node 2 → node 4 → node 2 → ...
                                          ↑_________________________|

Because `nums[3] == nums[4] == 2`, two nodes both point to node 2.
This creates a cycle, and the entry point of that cycle is the duplicate.

Index 0 is a safe starting point because all values are in `[1, n]` —
no value ever points back to index 0, so it can't be inside the cycle.

### Phase 1 — Detect the cycle

`slow` advances one step (`slow = nums[slow]`), `fast` advances two
(`fast = nums[nums[fast]]`). They meet somewhere inside the cycle.

### Phase 2 — Find the cycle entry

Reset `slow` to `nums[0]` and advance both one step at a time. By the
mathematical property of Floyd's algorithm, they meet exactly at the
cycle entry — the duplicate value.

## Complexity

- **Time:** O(n)
- **Space:** O(1) — no extra data structure; the array is not modified

## Trace with nums = [1, 3, 4, 2, 2]

    Phase 1:
      slow=nums[0]=1, fast=nums[0]=1
      slow=nums[1]=3, fast=nums[nums[1]]=nums[3]=2
      slow=nums[3]=2, fast=nums[nums[2]]=nums[4]=2
      slow=nums[2]=4, fast=nums[nums[2]]=nums[4]=2  ← wait, recheck
      slow=nums[2]=4, fast=nums[nums[4]]=nums[2]=4
      slow==fast==4 → cycle found

    Phase 2:
      slow=nums[0]=1
      slow=nums[1]=3, fast=nums[4]=2
      slow=nums[3]=2, fast=nums[2]=4
      slow=nums[2]=4, fast=nums[4]=2
      ... converge at 2

    return 2 ✅

## Why not simpler approaches?

- **Sorting:** O(n log n) time and O(1) space if in-place, but modifies the array.
- **HashSet:** O(n) time and O(n) space — violates the O(1) space constraint.
- **Sum formula:** only works when there is exactly one duplicate appearing
  exactly twice; fails with multiple occurrences.
- **Floyd's:** O(n) time, O(1) space, non-destructive. ✅

## Connection to Linked List Cycle (#141)

| #141 Linked List Cycle | #287 Find the Duplicate |
|---|---|
| Explicit list with node objects | Array treated as implicit list |
| `node.next` is the pointer | `nums[i]` is the pointer |
| Detects whether a cycle exists | Finds where the cycle starts |
| Phase 1 only needed | Both phases needed |

The algorithm is identical — the only difference is what constitutes a
"node" and what constitutes a "pointer".
