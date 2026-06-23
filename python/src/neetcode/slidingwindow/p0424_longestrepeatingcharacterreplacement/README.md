# 424. Longest Repeating Character Replacement — Python

🔗 [LeetCode](https://leetcode.com/problems/longest-repeating-character-replacement/) | 📺 [NeetCode](https://neetcode.io/problems/longest-repeating-substring-with-replacement)

**Difficulty:** Medium
**Category:** Sliding Window

## Problem

Given a string `s` of uppercase letters and an integer `k`, return the
length of the longest substring that can be turned into a single repeated
character by replacing at most `k` characters.

## Key formula

For any window, the minimum replacements needed to make it uniform are:

    replacements = window_size - count_of_most_frequent_character

A window is **valid** when `replacements <= k`, i.e.:

    (right - left + 1) - countMax <= k

## Approach

**Variable-size sliding window with a frequency dict.**

1. Extend `right` one step, update `count` and `countMax`.
2. If the window is invalid, shrink from the left.
3. Update `maxLen`.

### The stale countMax trick

`countMax` is never decremented when shrinking. This is safe because
`maxLen` can only grow when a genuinely larger `countMax` appears —
a stale value just prevents the window from growing, never causes a
wrong answer.

## Complexity

- **Time:** O(n)
- **Space:** O(1) — at most 26 keys in the dict

## Java vs Python

    // Java
    count[s.charAt(right) - 'A']++;
    countMax = Math.max(countMax, count[s.charAt(right) - 'A']);

    # Python
    count[c] = count.get(c, 0) + 1
    countMax = max(countMax, count[c])

Key differences:
- Python dict with `get(c, 0)` replaces Java's `int[26]` array
- No need for `c - 'A'` index trick — Python uses the char directly
- `while` loop for shrinking instead of `if` (both work, while is safer)
