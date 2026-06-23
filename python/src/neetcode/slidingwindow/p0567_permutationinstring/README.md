# 567. Permutation in String — Python

🔗 [LeetCode](https://leetcode.com/problems/permutation-in-string/) | 📺 [NeetCode](https://neetcode.io/problems/permutation-string)

**Difficulty:** Medium
**Category:** Sliding Window

## Problem

Return `True` if any permutation of `s1` exists as a substring of `s2`.

## Key insight

A substring of `s2` is a permutation of `s1` iff it has the same character
frequencies as `s1`. Use a fixed-size sliding window of length `len(s1)`.

## Approach A — Counter comparison

Build `Counter(s1)` and `Counter(s2[:len(s1)])` for the first window.
Slide the window: add the incoming character, remove the outgoing one,
delete keys with count 0, then compare.

Deleting zero-count keys is essential — `Counter({'a':1, 'b':0})` does
not equal `Counter({'a':1})` in Python.

## Approach B — int[26] arrays (mirrors Java)

Use two lists of 26 integers. List equality (`==`) compares element by
element, so no key-cleanup is needed. `ord(c) - ord('a')` maps each
lowercase letter to index 0-25.

## Complexity

Both approaches:
- **Time:** O(n)
- **Space:** O(1)

## Java vs Python

    // Java
    Arrays.equals(countS1, countWin)   // int[26] comparison

    # Python — Counter
    count_win == count_s1              // dict comparison (need to del 0-keys)

    # Python — list
    count_s1 == count_win              // list comparison (no cleanup needed)

## Why delete zero-count keys

    Counter("ab") == {'a':1, 'b':1}           # True
    Counter("ab") == {'a':1, 'b':1, 'c':0}    # False ← breaks equality!
