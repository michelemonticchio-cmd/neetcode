# 567. Permutation in String

🔗 [LeetCode](https://leetcode.com/problems/permutation-in-string/) | 📺 [NeetCode](https://neetcode.io/problems/permutation-string)

**Difficulty:** Medium
**Category:** Sliding Window

## Problem

Given two strings `s1` and `s2`, return `true` if any permutation of `s1`
exists as a substring of `s2`, `false` otherwise. Both strings contain only
lowercase English letters.

## Key observation

A substring of `s2` is a permutation of `s1` if and only if it has exactly
the same **character frequencies** as `s1`. The order of characters doesn't
matter — only the counts.

    "abc" and "cab" and "bca" all have the same frequencies: {a:1, b:1, c:1}

## Approach

**Fixed-size sliding window with two frequency arrays.**

The window has a fixed length equal to `s1.length()`. At each step:
1. The rightmost character of the new window enters (`countWin[right]++`).
2. The leftmost character of the old window leaves (`countWin[right - len]--`).
3. Compare `countWin` against `countS1` with `Arrays.equals`.

If they match at any position, a valid permutation was found.

## Why a frequency array and not a HashSet

A `HashSet` only tracks *presence*, not *count*. For permutation checking
we need counts, because `s1 = "aab"` requires two 'a's — a set would
incorrectly accept any window containing at least one 'a' and one 'b'.

Two `int[26]` arrays (one per lowercase letter) carry the full frequency
information and support O(26) = O(1) comparison via `Arrays.equals`.

## Complexity

- **Time:** O(n) where n = `s2.length()` — the initialization loop runs
  in O(m) where m = `s1.length()` ≤ n; each subsequent step is O(1) plus
  O(26) for the comparison.
- **Space:** O(1) — two fixed-size arrays of 26 integers.

## Trace with s1 = "abc", s2 = "lecabee"

    len = 3, countS1 = {a:1, b:1, c:1}

    Initial window s2[0..2] = "lec": countWin = {l:1, e:1, c:1} ≠ countS1
    right=3: add 'a', remove 'l' → "eca": {e:1, c:1, a:1} ≠ countS1
    right=4: add 'b', remove 'e' → "cab": {c:1, a:1, b:1} = countS1 ✅ return true

## Pitfalls

- **Forgetting the initial window check.** The first `if (Arrays.equals(...))`
  before the loop handles the case where s2 starts with a permutation of s1.
  Without it, windows starting at index 0 are never checked.
- **Wrong left index when removing.** The outgoing character is at
  `right - len`, not `right - len + 1` or `left`. Verify with a small
  example if unsure.
- **Using a set instead of an array.** Sets lose frequency information
  (see "Why a frequency array" above).

## Connection to Valid Anagram (#242)

That problem asks "are these two strings anagrams?" — which is equivalent
to asking whether the *entire* s2 is a permutation of s1. This problem
asks whether *any window* of s2 is. The core check (`Arrays.equals` on
frequency arrays) is identical; only the loop around it changes.

## Notes

This is the canonical **fixed-size sliding window** problem. The same
template — initialize, then slide while updating frequencies and comparing
— appears in:

- **Find All Anagrams in a String** (#438): same logic, collect all match
  positions instead of returning on the first one.
- **Minimum Window Substring** (#76): variable-size window; harder because
  you shrink from the left when the window becomes valid (minimizing length)
  rather than when it becomes invalid.
