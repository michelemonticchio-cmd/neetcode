# 21. Merge Two Sorted Lists

🔗 [LeetCode](https://leetcode.com/problems/merge-two-sorted-lists/) | 📺 [NeetCode](https://neetcode.io/problems/merge-two-sorted-linked-lists)

**Difficulty:** Easy
**Category:** Linked List

## Problem

Given the heads of two sorted linked lists `list1` and `list2`, merge them
into one sorted linked list built from the nodes of both lists. Return the
head of the merged list.

## Approach

**Dummy head + iterative merge.**

A "dummy" node before the real result eliminates the special case of
initializing the head of the result list — `dummy.next` is always the
true head.

While both lists have remaining nodes, compare the two current heads and
append the smaller one to the result, advancing that list's pointer.
When one list is exhausted, the other is already sorted, so it can be
appended directly without further comparisons.

## Complexity

- **Time:** O(m + n) — every node is visited exactly once
- **Space:** O(1) — nodes are reused in place; only the dummy node is new

## Trace with list1 = [1,2,4], list2 = [1,3,5]

    dummy→?   curr=dummy

    list1.val(1) <= list2.val(1) → take list1:  dummy→1,  list1=2→4
    list1.val(2) >  list2.val(1) → take list2:  dummy→1→1, list2=3→5
    list1.val(2) <= list2.val(3) → take list1:  dummy→1→1→2, list1=4
    list1.val(4) >  list2.val(3) → take list2:  dummy→1→1→2→3, list2=5
    list1.val(4) <= list2.val(5) → take list1:  dummy→1→1→2→3→4, list1=null

    list1 exhausted → curr.next = list2 (5→null)
    return dummy.next = 1→1→2→3→4→5 ✅

## Edge cases

- **One or both lists empty:** if `list1 == null`, the while loop never
  runs and `curr.next = list2` returns `list2` directly (or `null` if
  both are empty). No special guards needed.
- **Lists of different lengths:** the trailing `curr.next = ...` handles
  all remaining nodes of the longer list in one assignment.
- **Equal values:** `<=` ensures stability — when values are equal, the
  node from `list1` comes first, preserving relative order within each list.

## Why the dummy node works

Without the dummy, you'd need to figure out which list's first node is
the real head before entering the loop — adding an `if` before the `while`.
The dummy absorbs that initialization: every node, including the very first,
is appended with the same `curr.next = ...` / `curr = curr.next` pattern.

## Notes

The dummy-head pattern is ubiquitous in linked list problems:

- **Merge K Sorted Lists** (#23) — generalization using a min-heap.
- **Remove Nth Node From End of List** (#19) — dummy simplifies edge case
  when the head itself is removed.
- **Reorder List** (#143) — split, reverse, merge.
- **Sort List** (#148) — merge sort on a linked list uses this merge step.
