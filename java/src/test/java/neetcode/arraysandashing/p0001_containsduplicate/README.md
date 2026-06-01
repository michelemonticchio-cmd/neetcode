# 217. Contains Duplicate

🔗 [LeetCode](https://leetcode.com/problems/contains-duplicate/) | 📺 [NeetCode](https://neetcode.io/problems/duplicate-integer)

**Difficulty:** Easy
**Category:** Arrays & Hashing

## Problem

Given an integer array `nums`, return `true` if any value appears at least
twice in the array, and `false` if every element is distinct.

## Approach

**HashSet — single pass.** Iterate through the array keeping a set of
already-seen numbers. For each element, if it's already in the set we
found a duplicate; otherwise add it and continue.

## Complexity

- **Time:** O(n) — single pass, HashSet operations are O(1) average
- **Space:** O(n) — in the worst case the set stores all n elements

## Alternative approaches

- **Brute force O(n²):** nested loops comparing each pair. Too slow on large inputs.
- **Sort + adjacent check:** O(n log n) time, O(1) extra space. Useful when
  memory is constrained and modifying the input is acceptable.
