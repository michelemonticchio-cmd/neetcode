# 3. Longest Substring Without Repeating Characters — Python

🔗 [LeetCode](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | 📺 [NeetCode](https://neetcode.io/problems/longest-substring-without-duplicates)

**Difficulty:** Medium
**Category:** Sliding Window

## Problem

Given a string `s`, return the length of the longest substring with no
duplicate characters.

## Approach A — Set (sliding window)

Maintain a `set` mirroring the current window. When a duplicate enters,
shrink from the left until it disappears.

    window = set()
    left = 0
    for right in range(len(s)):
        while s[right] in window:
            window.remove(s[left])
            left += 1
        window.add(s[right])
        maxLen = max(maxLen, right - left + 1)

## Approach B — Dict with last seen index

Store `character → last seen index`. When a duplicate is found inside the
current window, jump `left` directly to `lastIndex + 1`.

    for right, c in enumerate(s):
        if c in seen and seen[c] >= left:
            left = seen[c] + 1
        seen[c] = right
        maxLen = max(maxLen, right - left + 1)

The condition `seen[c] >= left` ensures we only jump if the duplicate is
actually inside the current window, not from a previous window.

## Complexity

Both approaches:
- **Time:** O(n)
- **Space:** O(min(n, alphabet size))

## Python tools used

- `for right, c in enumerate(s)` — index + character together
- `x in set` — O(1) membership test
- `set.remove(x)` — removes x, raises KeyError if missing
- `set.discard(x)` — removes x silently if missing
