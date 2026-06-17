# 25. Reverse Nodes in K-Group

🔗 [LeetCode](https://leetcode.com/problems/reverse-nodes-in-k-group/) | 📺 [NeetCode](https://neetcode.io/problems/reverse-nodes-in-k-group)

**Difficulty:** Hard
**Category:** Linked List

## Problem

Given the head of a linked list and a positive integer `k`, reverse every
`k` consecutive nodes. If the remaining nodes are fewer than `k`, leave
them as-is. Return the modified list head.

Only node `next` pointers may be changed — not values.

## Approach

**Dummy node + per-group detach-reverse-reconnect.**

A dummy node before `head` lets `groupPrev` start one step before the first
group, giving a uniform reconnection pattern for every group (including the
first).

### Per-group steps

    groupPrev → [kHead → ... → kTail] → nextGroup → ...

1. **`getKth(groupPrev, k)`** — advance k steps from `groupPrev`. Returns
   `kTail` if k nodes exist, `null` otherwise (stop).
2. **Detach and reverse** — set `kTail.next = null`, then reverse the
   segment `[kHead..kTail]`. After reversal `kTail` is the new head and
   `kHead` is the new tail (pointing to null).
3. **Reconnect** —
   `groupPrev.next = kTail` (attach new head),
   `kHead.next = nextGroup` (attach new tail to remainder),
   `groupPrev = kHead` (advance for next iteration).

### Why kHead becomes the new tail

The standard in-place reversal (Reverse Linked List #206) flips all `next`
pointers. The original head ends up pointing to `null` (because we
pre-set `kTail.next = null` to isolate the group). So after `reverse(kHead)`,
`kHead.next == null` — it is the tail of the reversed group.

## Complexity

- **Time:** O(n) — `getKth` and `reverse` each visit every node at most
  once across all iterations
- **Space:** O(1) — only pointer variables

## Trace with head = [1→2→3→4→5→6], k = 3

    dummy→1→2→3→4→5→6,  groupPrev=dummy

    Group 1:
      getKth(dummy, 3) = node(3)   kHead=1, kTail=3, nextGroup=4
      detach: 3.next=null  →  1→2→3
      reverse: 3→2→1
      reconnect: dummy.next=3,  1.next=4,  groupPrev=1
      list: dummy→3→2→1→4→5→6

    Group 2:
      getKth(1, 3) = node(6)   kHead=4, kTail=6, nextGroup=null
      detach: 6.next=null  →  4→5→6
      reverse: 6→5→4
      reconnect: 1.next=6,  4.next=null,  groupPrev=4
      list: dummy→3→2→1→6→5→4

    getKth(4, 3) = null → break

    return dummy.next = 3→2→1→6→5→4 ✅

## Trace with head = [1→2→3→4→5], k = 2 (odd-length, tail left as-is)

    Group 1: reverse [1→2] → [2→1],  list: 2→1→3→4→5
    Group 2: reverse [3→4] → [4→3],  list: 2→1→4→3→5
    Group 3: getKth(3, 2) = null (only node 5 left) → break

    return 2→1→4→3→5 ✅

## Pitfalls

- **Pre-setting `kTail.next = null`.** Without isolating the group, the
  reversal would continue into the next group and corrupt the list.
- **Reconnecting in the right order.** `groupPrev.next = kTail` must come
  before `kHead.next = nextGroup` — if done in reverse, you lose the
  reference to `nextGroup` via `groupPrev.next`.
- **Advancing `groupPrev` to `kHead`**, not `kTail`. After reconnection
  `kHead` is the tail of the just-reversed group — the correct starting
  point for the next group's `groupPrev`.

## Notes

Reverse Nodes in K-Group chains three patterns from earlier problems:

- **Reverse Linked List** (#206) — the core `reverse()` helper is identical.
- **Remove Nth Node** (#19) — `getKth()` uses the same counter pattern.
- **Dummy node** — same sentinel trick as Merge Two Sorted Lists (#21) and
  Remove Nth Node (#19).

Recognizing that a hard problem decomposes into known sub-problems is one
of the most valuable interview skills.
