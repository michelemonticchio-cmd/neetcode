# 138. Copy List with Random Pointer

🔗 [LeetCode](https://leetcode.com/problems/copy-list-with-random-pointer/) | 📺 [NeetCode](https://neetcode.io/problems/copy-linked-list-with-random-pointer)

**Difficulty:** Medium
**Category:** Linked List

## Problem

Each node of a linked list has three fields: `val`, `next`, and `random`
(which points to any node in the list, or `null`). Return a **deep copy**
of the list: brand-new nodes with identical values and pointer structure,
sharing no references with the original.

## Approach

**Two-pass algorithm with a HashMap.**

### Why one pass isn't enough

When copying node A whose `random` points to node C, C's clone may not
exist yet. The map solves this by decoupling creation from linking.

### Pass 1 — Create all clones

Traverse the original list and insert `(originalNode → cloneNode)` into
a `HashMap`. Each clone gets only its `val`; `next` and `random` are left
null for now.

### Pass 2 — Wire the pointers

Traverse again. For each original node, look up its clone and set:

    clone.next   = map.get(original.next)
    clone.random = map.get(original.random)

`HashMap.get(null)` returns `null` in Java, so tail nodes (`next == null`)
and nodes with no random target (`random == null`) require no special cases.

## Complexity

- **Time:** O(n) — two linear passes
- **Space:** O(n) — one map entry per node

## Trace

    Original: 1 → 2 → 3, random: 1→3, 2→1, 3→null

    Pass 1: map = {orig1→clone1, orig2→clone2, orig3→clone3}

    Pass 2:
      clone1.next=clone2, clone1.random=clone3
      clone2.next=clone3, clone2.random=clone1
      clone3.next=null,   clone3.random=null

    return map.get(orig1) = clone1 ✅

## Pitfall: deep copy vs shallow copy

Simply doing `clone.next = original.next` would make the clone point to
**original** nodes, not cloned ones — a shallow copy. The map ensures
every pointer in the clone graph refers to another clone, never to an
original node.

## Notes

The two-pass map approach is the standard solution. An O(1)-space variant
exists by interleaving clones into the original list:

    orig1 → clone1 → orig2 → clone2 → ...

then wiring `random` via `orig.random.next` (which is the clone of
`orig.random`), then separating the two lists. It's more error-prone and
rarely expected unless explicitly asked for.
