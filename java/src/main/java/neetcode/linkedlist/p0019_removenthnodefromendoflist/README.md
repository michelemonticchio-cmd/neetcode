# 19. Remove Nth Node From End of List

🔗 [LeetCode](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) | 📺 [NeetCode](https://neetcode.io/problems/remove-node-from-end-of-linked-list)

**Difficulty:** Medium
**Category:** Linked List

## Problem

Given the head of a linked list and an integer `n`, remove the nth node
from the **end** of the list and return the head.

## Approach

**Two pointers with a fixed gap of n nodes.**

The key insight: if `fast` is exactly `n` nodes ahead of `slow`, then when
`fast` reaches the last node (`fast.next == null`), `slow` is exactly at
the node **just before** the one to remove.

A **dummy node** before `head` handles the edge case where the head itself
must be removed (when `n == list length`) without any special-casing.

### Algorithm

1. Place both `slow` and `fast` at `dummy`.
2. Advance `fast` by `n` steps — this creates the gap.
3. Advance both one step at a time until `fast.next == null`.
4. `slow.next` is the node to remove: `slow.next = slow.next.next`.
5. Return `dummy.next`.

## Complexity

- **Time:** O(L) — one pass through the list, L = list length
- **Space:** O(1) — two pointer variables and a dummy node

## Trace with head = [1,2,3,4,5], n = 2

    dummy→1→2→3→4→5→null
    slow=dummy, fast=dummy

    Advance fast by 2: fast=1, fast=2

    Advance both until fast.next==null:
      slow=1, fast=3
      slow=2, fast=4
      slow=3, fast=5  → fast.next==null → stop

    slow=3, remove slow.next=4: slow.next = 5
    return dummy.next = 1→2→3→5 ✅

## Trace with head = [5], n = 1 (remove the only node)

    dummy→5→null
    slow=dummy, fast=dummy

    Advance fast by 1: fast=5

    fast.next==null immediately → skip while loop

    slow=dummy, remove slow.next=5: slow.next = null
    return dummy.next = null ✅

## Why dummy is essential

Without the dummy, removing the head requires a separate check:

    if (n == listLength) return head.next;

With the dummy, `slow` starts one step before `head`, so when `fast`
exhausts the gap at exactly the head, `slow` is at the dummy and
`slow.next = slow.next.next` removes `head` naturally — no special case.

## Pitfalls

- **`fast.next != null` not `fast != null`.** Stopping when `fast == null`
  would leave `slow` one step too far, pointing at the node to remove
  rather than the one before it.
- **Gap is n, not n-1.** Starting both pointers at dummy (not head) and
  advancing fast by exactly n steps gives the correct one-node-before
  position for slow.

## Notes

The "fixed gap between two pointers" technique appears wherever you need
positional information relative to the **end** of a list without knowing
its length upfront:

- **Middle of the Linked List** (#876) — gap of half the list (slow/fast
  at 1x/2x speed instead of a fixed offset).
- **Linked List Cycle** (#141) — gap grows until the pointers meet inside
  a cycle.
