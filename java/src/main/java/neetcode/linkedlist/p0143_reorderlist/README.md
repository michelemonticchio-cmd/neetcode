# 143. Reorder List

🔗 [LeetCode](https://leetcode.com/problems/reorder-list/) | 📺 [NeetCode](https://neetcode.io/problems/reorder-linked-list)

**Difficulty:** Medium
**Category:** Linked List

## Problem

Given the head of a singly linked list, reorder it **in place** so that
nodes alternate between the front and back of the original list:

    [0, 1, 2, ..., n-1]  →  [0, n-1, 1, n-2, 2, n-3, ...]

Node values must not be changed — only the nodes themselves may be relinked.

## Approach

**Three-pass algorithm: split → reverse → merge.**

### Step 1 — Find the middle and split

Use slow/fast pointers: `slow` advances one step, `fast` two. When `fast`
can no longer advance two steps, `slow` is at the middle. Terminate the
first half by setting `slow.next = null`.

    0→1→2→3→4→5→6
    slow stops at 3 → first: 0→1→2→3,  second: 4→5→6

### Step 2 — Reverse the second half

Standard iterative reverse (same as #206):

    second: 4→5→6  →  6→5→4

### Step 3 — Merge alternating nodes

Interleave nodes from `first` and `second` one at a time:

    first=0→1→2→3,  second=6→5→4

    take 0, take 6  →  0→6→...
    take 1, take 5  →  0→6→1→5→...
    take 2, take 4  →  0→6→1→5→2→4→...
    take 3, second exhausted → 0→6→1→5→2→4→3 ✅

## Complexity

- **Time:** O(n) — three linear passes (find middle, reverse, merge)
- **Space:** O(1) — all operations are in-place with a constant number
  of pointer variables

## Why the method returns void

The list is modified **in place** — the original `head` node stays the
head and all relinking happens via `next` pointer assignments. There is
nothing to return; the caller's reference to `head` already sees the
reordered list.

## Pitfalls

- **Fast pointer guard: `fast.next != null && fast.next.next != null`.**
  Checking only `fast.next.next != null` would throw a NullPointerException
  when `fast.next` is null (even-length list). Both conditions are needed.
- **Terminate the first half.** Forgetting `slow.next = null` leaves the
  first half connected to the second, creating a cycle during the merge step.
- **Merge termination condition: `while (second != null)`.**
  After reversing, the second half is shorter or equal in length. When
  `second` is exhausted, `first` holds the middle node (already in place),
  so no additional step is needed.

## Notes

Reorder List is the canonical "combination" problem of the Linked List
category — it chains three foundational techniques:

- **Slow/fast pointers** to find the middle (#876 Middle of the Linked List)
- **In-place reversal** (#206 Reverse Linked List)
- **Merge with alternation** (similar to #21 Merge Two Sorted Lists but
  without a value comparison)

Recognizing that a hard linked list problem can be broken into simpler
sub-problems you already know is one of the most valuable skills in
interviews.
