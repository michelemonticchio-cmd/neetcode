# 23. Merge K Sorted Lists

🔗 [LeetCode](https://leetcode.com/problems/merge-k-sorted-lists/) | 📺 [NeetCode](https://neetcode.io/problems/merge-k-sorted-linked-lists)

**Difficulty:** Hard
**Category:** Linked List

## Problem

Given an array of `k` sorted linked lists, merge them into one sorted
linked list and return its head.

## Approach

**Min heap (PriorityQueue) seeded with the k list heads.**

### Why not scan all k heads linearly?

At each step, finding the minimum among k heads by linear scan costs O(k).
With n total nodes that's O(n·k) — too slow for large k.

### The heap insight

A min heap always exposes its minimum in O(1) and updates in O(log k).
Since we only ever need the current minimum among the k "frontier" nodes
(one per list), we keep exactly those k nodes in the heap at any time.

### Algorithm

1. Insert the head of every non-null list into the heap.
2. While the heap is non-empty:
   - `poll()` the minimum node → append to result.
   - If that node has a `next`, `offer(next)` into the heap.
3. Return `dummy.next`.

The heap never holds more than k nodes because each extraction is followed
by at most one insertion (the successor of the extracted node).

## Complexity

- **Time:** O(n log k) — each of the n nodes is inserted and extracted
  once; each operation costs O(log k).
- **Space:** O(k) — the heap holds at most k nodes at any time.

## Trace with lists = [[1→4→5], [1→3→4], [2→6]]

    Heap init: [1(L1), 1(L2), 2(L3)]

    poll 1(L1) → result: 1,        offer 4(L1) → heap: [1(L2), 2(L3), 4(L1)]
    poll 1(L2) → result: 1→1,      offer 3(L2) → heap: [2(L3), 3(L2), 4(L1)]
    poll 2(L3) → result: 1→1→2,    offer 6(L3) → heap: [3(L2), 4(L1), 6(L3)]
    poll 3(L2) → result: 1→1→2→3,  offer 4(L2) → heap: [4(L1), 4(L2), 6(L3)]
    poll 4(L1) → result: ...→4,    offer 5(L1) → heap: [4(L2), 5(L1), 6(L3)]
    poll 4(L2) → result: ...→4→4,  no next     → heap: [5(L1), 6(L3)]
    poll 5(L1) → result: ...→5,    no next     → heap: [6(L3)]
    poll 6(L3) → result: ...→6,    no next     → heap: []

    return 1→1→2→3→4→4→5→6 ✅

## Comparator note

`(a, b) -> a.val - b.val` creates a min heap ordered by node value.
For production code, prefer `Integer.compare(a.val, b.val)` to avoid
integer overflow when values are near `Integer.MIN_VALUE`.

## Alternative approaches

- **Brute force:** collect all values, sort, rebuild — O(n log n) time,
  O(n) space. Works but ignores the sorted structure of each list.
- **Pair-wise merge:** merge lists two at a time like merge sort —
  O(n log k) time, O(1) extra space, but more complex to implement.
- **Min heap (this solution):** O(n log k) time, O(k) space — clean and
  idiomatic.

## Notes

This problem generalizes #21 Merge Two Sorted Lists from k=2 to arbitrary
k. The min heap replaces the simple `if (l1.val <= l2.val)` comparison with
an O(log k) priority queue operation, keeping the same overall structure.
