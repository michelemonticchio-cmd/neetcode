# 1. Two Sum — Python

🔗 [LeetCode](https://leetcode.com/problems/two-sum/) | 📺 [NeetCode](https://neetcode.io/problems/two-integer-sum)

**Difficulty:** Easy
**Category:** Arrays & Hashing

## Problem

Given an array of integers `nums` and an integer `target`, return the
indices of the two numbers that add up to `target`. Exactly one solution
always exists.

## Approach

**Single pass with a HashMap.**

For each element `n` at index `i`, compute `diff = target - n`.
If `diff` is already in the map, the pair is found — return both indices.
Otherwise store `n → i` for future lookups.

    seen = {}
    for i, n in enumerate(nums):
        diff = target - n
        if diff in seen:
            return [seen[diff], i]
        seen[n] = i

## Complexity

- **Time:** O(n) — one pass, O(1) dict lookup at each step
- **Space:** O(n) — dict holds at most n entries

## Java vs Python

    // Java
    Map<Integer, Integer> seen = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int diff = target - nums[i];
        if (seen.containsKey(diff)) return new int[]{seen.get(diff), i};
        seen.put(nums[i], i);
    }

    # Python
    seen = {}
    for i, n in enumerate(nums):
        diff = target - n
        if diff in seen:
            return [seen[diff], i]
        seen[n] = i

Key differences:
- `enumerate(nums)` replaces the index-based for loop
- `diff in seen` replaces `seen.containsKey(diff)`
- `seen[diff]` replaces `seen.get(diff)`
- `seen[n] = i` replaces `seen.put(n, i)`
