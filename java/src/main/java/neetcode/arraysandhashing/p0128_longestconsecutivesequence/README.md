# 128. Longest Consecutive Sequence

🔗 [LeetCode](https://leetcode.com/problems/longest-consecutive-sequence/) | 📺 [NeetCode](https://neetcode.io/problems/longest-consecutive-sequence)

**Difficulty:** Medium
**Category:** Arrays & Hashing

## Problem

Given an unsorted array of integers `nums`, return *the length* of the longest
sequence of consecutive integers present in the array.

A consecutive sequence is one where each element is exactly 1 greater than the
previous (e.g. `[1, 2, 3, 4]`). The elements do not need to appear consecutively
in the input array.

**Constraint:** the algorithm must run in O(n) time. Sorting (O(n log n)) is
not acceptable.

## Approach

**HashSet + start-of-sequence detection.**

The naive approach (try to extend a sequence from every element) is O(n²)
because each number could be visited as part of many sequences. The trick is
to ensure **each sequence is counted exactly once**, by only starting the
forward walk from the actual *beginning* of each sequence.

**Key insight:** a number `n` is the start of a sequence if and only if
`n - 1` is not in the set. Otherwise, `n` is "in the middle" of a longer
sequence that already started before it.

Algorithm:
1. Put all elements into a `HashSet<Integer>` for O(1) lookups.
2. For each `n` in the set, check if it's a sequence start.
3. If yes, walk forward: `n`, `n+1`, `n+2`, ... incrementing the length
   counter while each next value is in the set.
4. Track the maximum length seen.

## Why this is O(n)

It looks like O(n²) because of the nested while loop, but each number is
touched by a forward-walk **at most once across the entire execution** —
since each number belongs to exactly one sequence, and that sequence is
expanded from a single start. The total work of all inner loops summed
is O(n).

The start-of-sequence check (`!set.contains(n - 1)`) is what guarantees
the linear bound: without it, starting the walk from every element would
make the algorithm quadratic.

## Complexity

- **Time:** O(n) — each element is examined a constant number of times
- **Space:** O(n) — for the set storing all distinct elements

## Trace with nums = [2, 20, 4, 10, 3, 4, 5]

    set = {2, 20, 4, 10, 3, 5}   (duplicate 4 removed)

    n=2:  set.contains(1)? No  → start. Walk: 2, 3, 4, 5 → len=4
    n=20: set.contains(19)? No → start. Walk: 20        → len=1
    n=4:  set.contains(3)? Yes → skip (not a start)
    n=10: set.contains(9)? No  → start. Walk: 10        → len=1
    n=3:  set.contains(2)? Yes → skip
    n=5:  set.contains(4)? Yes → skip

    maxLen = 4

## Notes

This problem is a classic example of how a **constant-time lookup structure**
(HashSet/HashMap) can turn an apparently-quadratic problem into a linear one.
The combination "filter for entry points + bounded walk per entry point" is
a recurring pattern — you'll see it again in island-counting problems on
grids (DFS/BFS only from unvisited cells), interval merging, and several
graph traversals.

The lesson: when the brute-force solution revisits the same work, look for
a way to **identify a canonical starting point** (here: smallest of each
sequence) so each piece of work happens exactly once.
