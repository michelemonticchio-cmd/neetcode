# 242. Valid Anagram — Python

🔗 [LeetCode](https://leetcode.com/problems/valid-anagram/) | 📺 [NeetCode](https://neetcode.io/problems/is-anagram)

**Difficulty:** Easy
**Category:** Arrays & Hashing

## Problem

Given two strings `s` and `t`, return `True` if `t` is an anagram of `s`,
`False` otherwise. An anagram uses the exact same characters with the same
frequencies, in any order.

## Approach A — Counter one-liner

    return Counter(s) == Counter(t)

`Counter` builds a frequency map from any iterable in O(n). Two strings are
anagrams iff their frequency maps are identical. This is the most idiomatic
Python solution.

## Approach B — Explicit frequency dict

1. Early exit if `len(s) != len(t)`.
2. Increment counts for every character in `s`.
3. Decrement counts for every character in `t`.
4. If any count goes negative, `t` has a character more than `s` — not an anagram.

This mirrors the Java HashMap approach and short-circuits on the first
discrepancy instead of always scanning both strings fully.

## Complexity

Both approaches:
- **Time:** O(n) — two linear passes at most
- **Space:** O(k) — k = number of distinct characters (≤ 26 for lowercase ASCII)

## Java vs Python

    // Java
    Map<Character, Integer> count = new HashMap<>();
    for (char c : s.toCharArray())
        count.put(c, count.getOrDefault(c, 0) + 1);
    for (char c : t.toCharArray()) {
        count.put(c, count.getOrDefault(c, 0) - 1);
        if (count.get(c) < 0) return false;
    }
    return true;

    # Python equivalent
    count = {}
    for c in s:
        count[c] = count.get(c, 0) + 1
    for c in t:
        count[c] = count.get(c, 0) - 1
        if count[c] < 0:
            return False
    return True

Key differences:
- `count.get(c, 0)` replaces `count.getOrDefault(c, 0)`
- No `.toCharArray()` — Python strings are directly iterable
- `True/False` capitalized

## Notes

`Counter` from `collections` is the idiomatic Python tool for frequency
problems. It replaces the `HashMap<Character, Integer>` + `getOrDefault`
pattern entirely:

    Counter("racecar") == Counter("carrace")  # True
    Counter("rat")     == Counter("car")      # False
