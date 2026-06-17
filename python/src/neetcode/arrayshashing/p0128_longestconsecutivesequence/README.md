# 128. Longest Consecutive Sequence — Python

🔗 [LeetCode](https://leetcode.com/problems/longest-consecutive-sequence/) | 📺 [NeetCode](https://neetcode.io/problems/longest-consecutive-sequence)

**Difficulty:** Medium
**Category:** Arrays & Hashing

## Problem

Given an unsorted array of integers `nums`, return the length of the longest
consecutive sequence. Must run in O(n).

## Key insight

A number `n` is the **start** of a consecutive sequence if and only if
`n-1` is not in the array. Starting only from these "entry points" avoids
redundant work and keeps the algorithm linear.

## Approach

1. Build a `set` from `nums` for O(1) lookups.
2. For each `n` in `nums`:
   - If `n-1` is in the set → `n` is not a start, skip.
   - Otherwise → count how many consecutive numbers follow: n, n+1, n+2...
3. Return the maximum count seen.

## Why this is O(n) and not O(n²)

The inner `while` only runs for sequence starts. Every element is counted
in at most one sequence. Total iterations across all inner loops = n.

## Trace with nums = [100, 4, 200, 1, 3, 2]

    s = {1, 2, 3, 4, 100, 200}

    n=100: 99 not in s → start. 101 not in s → length=1
    n=4:   3 in s → skip
    n=200: 199 not in s → start. 201 not in s → length=1
    n=1:   0 not in s → start. 2,3,4 in s, 5 not → length=4 ✅
    n=3:   2 in s → skip
    n=2:   1 in s → skip

    return 4 ✅

## Complexity

- **Time:** O(n)
- **Space:** O(n) — the set

## Python tools used

- `set(nums)` — builds a set from a list in O(n)
- `not in` — negated membership test, like `!set.contains(x)` in Java
- `max(a, b)` — like `Math.max(a, b)` in Java

## Java vs Python

    // Java
    Set<Integer> s = new HashSet<>();
    for (int n : nums) s.add(n);
    int maxLen = 0;
    for (int n : nums) {
        if (!s.contains(n - 1)) {
            int length = 1;
            while (s.contains(n + length)) length++;
            maxLen = Math.max(maxLen, length);
        }
    }

    # Python
    s = set(nums)
    maxLen = 0
    for n in nums:
        if (n - 1) not in s:
            length = 1
            while (n + length) in s:
                length += 1
            maxLen = max(maxLen, length)
