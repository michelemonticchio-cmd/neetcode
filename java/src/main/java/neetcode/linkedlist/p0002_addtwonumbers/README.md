# 2. Add Two Numbers

🔗 [LeetCode](https://leetcode.com/problems/add-two-numbers/) | 📺 [NeetCode](https://neetcode.io/problems/add-two-numbers)

**Difficulty:** Medium
**Category:** Linked List

## Problem

Two non-negative integers are stored as linked lists in **reverse order**
(least-significant digit first). Return their sum as a linked list in the
same format.

Example: `342 + 465 = 807`

    l1: 2→4→3   l2: 5→6→4   result: 7→0→8

## Approach

**Digit-by-digit addition with a carry — one pass.**

The reversed storage is deliberate: the head of each list is the units
digit, matching the natural order of column-by-column addition. No
preprocessing or reversal is needed.

At each step:
1. Read the current digit from each list (or 0 if that list is exhausted).
2. Compute `sum = val1 + val2 + carry`.
3. The new digit is `sum % 10`; the new carry is `sum / 10`.
4. Append the digit to the result and advance both pointers.

The loop condition `l1 != null || l2 != null || carry != 0` ensures an
extra node is emitted when a carry survives after both lists are exhausted
(e.g. 99 + 1 = 100 needs a third node for the leading 1).

## Complexity

- **Time:** O(max(m, n)) — one pass over both lists in lock-step
- **Space:** O(max(m, n)) — the result list has at most max(m, n) + 1 nodes

## Trace with l1 = [2→4→3], l2 = [5→6→4]

    carry=0
    Step 1: 2+5+0=7,  carry=0, digit=7  → [7]
    Step 2: 4+6+0=10, carry=1, digit=0  → [7→0]
    Step 3: 3+4+1=8,  carry=0, digit=8  → [7→0→8]
    Both lists exhausted, carry=0 → stop
    return 7→0→8 = 807 ✅

## Trace with l1 = [9→9], l2 = [1] (99 + 1 = 100)

    Step 1: 9+1+0=10, carry=1, digit=0  → [0]
    Step 2: 9+0+1=10, carry=1, digit=0  → [0→0]
    Step 3: 0+0+1=1,  carry=0, digit=1  → [0→0→1]
    return 0→0→1 = 100 ✅

## Pitfalls

- **Forgetting `carry != 0` in the loop condition.** Without it, a
  final carry after both lists are exhausted would be silently dropped,
  giving a wrong answer (e.g. 99 + 1 = 00 instead of 100).
- **Not defaulting to 0 when a list is null.** If `l1` is shorter than
  `l2`, accessing `l1.val` after `l1` is exhausted throws a
  NullPointerException. The ternary `(l1 != null) ? l1.val : 0` handles
  this cleanly.

## Notes

The reversed-order storage is not just a quirk — it makes addition
straightforward. If the digits were stored in natural order (most
significant first), you'd need to either reverse both lists first or
use a stack to process them from right to left, adding O(n) overhead.

This problem is the linked list analogue of the classic "add binary
strings" or "add strings" problems — same digit-by-digit carry logic,
different input format.
