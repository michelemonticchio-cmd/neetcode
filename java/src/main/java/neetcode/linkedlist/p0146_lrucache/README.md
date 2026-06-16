# 146. LRU Cache

🔗 [LeetCode](https://leetcode.com/problems/lru-cache/) | 📺 [NeetCode](https://neetcode.io/problems/lru-cache)

**Difficulty:** Medium
**Category:** Linked List

## Problem

Implement a Least Recently Used (LRU) cache that supports:
- `get(key)` — return the value if the key exists, otherwise `-1`.
- `put(key, value)` — insert or update; if over capacity, evict the
  least recently used key.

Both operations must run in **O(1) average time**.

## Why HashMap + Doubly Linked List?

**HashMap alone** gives O(1) lookup but no ordering information — you
can't know which key was used least recently.

**Doubly linked list alone** maintains insertion/access order but lookup
is O(n).

**Together** they give O(1) for everything:
- HashMap: `key → node` for instant lookup.
- Doubly linked list: nodes ordered from LRU (head side) to MRU (tail side).

A doubly linked list is required (not singly linked) because removing an
arbitrary node in O(1) needs access to its predecessor — impossible without
a `prev` pointer.

## Sentinel nodes

Two dummy nodes (`head` and `tail`) sit at the boundaries of the list.
They never hold real data but eliminate all edge cases:

- LRU node is always `head.next`.
- MRU node is always `tail.prev`.
- Insertion and removal never need to check for null neighbors.

## Operations

### get(key)
1. Key not in map → return `-1`.
2. Key in map → get the node, move it to the tail (MRU), return its value.

### put(key, value)
1. Key exists → update value, move node to tail.
2. Key is new → create node, insert at tail, add to map.
   If `map.size() > capacity`: remove `head.next` from the list and from
   the map (evict the LRU entry).

## Complexity

- **Time:** O(1) for both `get` and `put`
- **Space:** O(capacity)

## Trace with capacity = 2

    put(1,1): map={1}, list: head↔[1]↔tail
    put(2,2): map={1,2}, list: head↔[1]↔[2]↔tail
    get(1):   move 1 to tail. list: head↔[2]↔[1]↔tail → return 1
    put(3,3): insert 3, over capacity → evict head.next=[2]
              map={1,3}, list: head↔[1]↔[3]↔tail
    get(2):   not in map → return -1 ✅
    get(3):   move 3 to tail → return 3 ✅

## Notes

LRU Cache is the prototypical "design a data structure" interview problem
because it requires combining two well-known structures in a non-obvious
way. The same HashMap + doubly linked list pattern appears in:

- **LFU Cache** (#460) — evicts the least *frequently* used key; requires
  an additional frequency layer.
- **All O(1) Data Structure** (#432) — generalizes the same idea to
  support getMaxKey and getMinKey in O(1).
