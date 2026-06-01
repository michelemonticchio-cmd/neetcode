# 242. Valid Anagram

🔗 [LeetCode](https://leetcode.com/problems/valid-anagram/) | 📺 [NeetCode](https://neetcode.io/problems/is-anagram)

**Difficulty:** Easy
**Category:** Arrays & Hashing

## Problem

Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`,
and `false` otherwise. Both strings consist of lowercase English letters.

## Approach

**Fixed-size character count array.** Since both strings contain only
lowercase English letters, use an `int[26]` array as a frequency counter:

1. If the strings have different lengths, they can't be anagrams — return false immediately.
2. Iterate over both strings in a single loop: increment the counter for the
   character in `s` and decrement it for the character in `t`.
3. If the strings are anagrams, all counters must be zero at the end.

The trick `c - 'a'` maps each character to its index (0..25) in the array.

## Complexity

- **Time:** O(n) — single pass over the strings; the final scan over the
  count array is O(26) = O(1)
- **Space:** O(1) — the count array always has 26 elements, independent of input size

## Alternative approaches

- **HashMap:** O(n) time, O(k) space where k is the alphabet size. Necessary
  if the input may contain Unicode characters.
- **Sort + compare:** O(n log n) time, O(n) space. Simpler to write but slower.

## Notes

The fixed-array approach exploits the constraint that the alphabet is small
and known in advance. It's a common pattern in string problems where the
character set is restricted.
