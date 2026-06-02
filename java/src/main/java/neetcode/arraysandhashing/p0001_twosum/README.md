# 1. Two Sum

🔗 [LeetCode](https://leetcode.com/problems/two-sum/) | 📺 [NeetCode](https://neetcode.io/problems/two-integer-sum)

**Difficulty:** Easy
**Category:** Arrays & Hashing

## Problem

Given an array of integers `nums` and an integer `target`, return the indices
`i` and `j` such that `nums[i] + nums[j] == target` and `i != j`.

Each input has exactly one solution. Return the answer with the smaller index first.

## Approach

**Single-pass HashMap.** For each element, compute the complement
`target - nums[i]`. If the complement is already in the map (i.e. we've
seen it earlier), we've found the pair — return its index and the current one.
Otherwise, store the current value with its index and continue.

Key insight: instead of searching the entire array for each element (O(n²)),
we build a lookup table on the fly and check against past elements only.

## Complexity

- **Time:** O(n) — single pass; HashMap `containsKey` and `put` are O(1) average
- **Space:** O(n) — worst case the map stores all n elements

## Alternative approaches

- **Brute force O(n²):** nested loops checking every pair. Discarded.
- **Sort + two pointers:** O(n log n) time, O(1) extra space, but we'd lose
  the original indices — so it doesn't fit this problem directly.

## Notes

This is the canonical "lookup with hash structure" pattern: whenever you find
yourself thinking *"for each element, I need to find something else in the
array"*, a HashMap turns the inner search from O(n) to O(1).
