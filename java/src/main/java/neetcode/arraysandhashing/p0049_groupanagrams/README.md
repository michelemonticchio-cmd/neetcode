# 49. Group Anagrams

🔗 [LeetCode](https://leetcode.com/problems/group-anagrams/) | 📺 [NeetCode](https://neetcode.io/problems/anagram-groups)

**Difficulty:** Medium
**Category:** Arrays & Hashing

## Problem

Given an array of strings `strs`, group all anagrams together into sublists.
The output may be returned in any order. Strings consist of lowercase English letters.

## Approach

**HashMap keyed by character-frequency signature.**
Two strings are anagrams if and only if they have the same letter frequencies.
For each string compute a fixed-size `int[26]` count array as its "signature",
then convert it to a `String` to be used as a HashMap key. Strings sharing
the same key are grouped together.

Key steps:
1. For each string, count letter frequencies into a `int[26]` array.
2. Convert the array to a String via `Arrays.toString(count)` — needed because
   Java arrays don't support content-based equality as HashMap keys.
3. Use `computeIfAbsent` to either create a new list for the key or append to
   the existing one in a single line.
4. Return all values of the map.

## Complexity

- **Time:** O(n · k) where n = number of strings, k = average string length.
  This is optimal: every character must be read at least once.
- **Space:** O(n · k) for the map storing all strings plus their signature keys.

## Alternative approaches

- **Sort each string as signature:** `Arrays.sort(s.toCharArray())` → use as
  key. O(n · k log k) time. Simpler but asymptotically slower.
- **More compact key:** instead of `Arrays.toString(count)`, build a string
  manually with `StringBuilder` (e.g. `"1#0#1#..."`). Same asymptotic
  complexity but lower constant factor — slightly less GC pressure.

## Notes

The lower bound for this problem is O(n · k) — any correct solution must
inspect every character of every string. The count-array approach achieves
this bound; the sort-based alternative is asymptotically worse.

This problem introduces two Java idioms worth remembering:
- **Array → String as map key**, to work around `int[]`'s identity-based equality
- **`computeIfAbsent(key, k -> new ArrayList<>()).add(value)`**, the canonical
  "multimap" pattern (one key → list of values) in one expressive line
