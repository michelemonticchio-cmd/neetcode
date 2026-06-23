# 76. Minimum Window Substring — Python

🔗 [LeetCode](https://leetcode.com/problems/minimum-window-substring/) | 📺 [NeetCode](https://neetcode.io/problems/minimum-window-with-characters)

**Difficulty:** Hard
**Category:** Sliding Window

## Problem

Given strings `s` and `t`, return the shortest substring of `s` that
contains every character of `t` (including duplicates). Return `""` if
no such substring exists.

## Approach

**Variable-size sliding window with need/window/formed/required.**

- `need = Counter(t)` — required frequencies for each character in t.
- `window = {}` — current frequencies in the sliding window.
- `required = len(need)` — number of distinct characters to satisfy.
- `formed` — how many distinct characters are currently satisfied.

Expand `right` until `formed == required` (valid window), then shrink
from `left` to minimize, recording the best result each time.

## Why formed/required instead of comparing dicts

Comparing two dicts at every step would be O(|t|) per step → O(n·|t|)
total. The `formed` counter reduces this to O(1) per step — only
increment/decrement when a character's count crosses the exact threshold.

## Complexity

- **Time:** O(|s| + |t|)
- **Space:** O(|t|)

## Java vs Python

    // Java
    map.getOrDefault(c, 0) + 1
    s.substring(minLeft, minLeft + minLen)
    Integer.MAX_VALUE

    # Python
    window.get(c, 0) + 1
    s[minLeft:minLeft + minLen]
    float("inf")
