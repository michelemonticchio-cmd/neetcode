# 424. Longest Repeating Character Replacement

🔗 [LeetCode](https://leetcode.com/problems/longest-repeating-character-replacement/) | 📺 [NeetCode](https://neetcode.io/problems/longest-repeating-substring-with-replacement)

**Difficulty:** Medium
**Category:** Sliding Window

## Problem

Given a string `s` of uppercase English characters and an integer `k`,
return the length of the longest substring containing the same letter
after performing **at most `k` character replacements**.

## Approach

**Variable-size sliding window with a frequency counter.**

For any window `[left, right]`, the minimum number of replacements needed
to make all characters equal is:

    replacements_needed = (window_size) - (count of most frequent char in window)

That's because the cheapest plan is always to keep the most frequent
character and replace all others. So a window is **valid** when:

    (right - left + 1) - countMax <= k

The algorithm:
1. Extend `right` by one position, incrementing the count of the new char
   and updating `countMax`.
2. If the window is no longer valid, shrink from the left until it is.
3. Update `maxLen` with the current window size.

## The "stale countMax" trick

A subtle but important point: when we shrink the window, we **do not**
recompute `countMax`. It may become an overestimate (referring to a
character that is no longer the most frequent in the current window).

**Why this is fine:** `maxLen` represents "the largest valid window ever
seen". To grow `maxLen` we'd need a window strictly larger than any past
valid window, which requires a strictly larger `countMax` to keep the
`(size - countMax) <= k` inequality satisfied. So a stale `countMax`
never causes us to *grow* `maxLen` incorrectly.

This trick lets the algorithm run in pure O(n) without scanning the 26
counts at each iteration.

## Complexity

- **Time:** O(n) — each character is added and removed at most once
- **Space:** O(1) — fixed 26-entry counter array

## Visualizing with s = "AAABABB", k = 1

    right=0..2: window "AAA", countMax=3, size=3, need=0 → valid → maxLen=3
    right=3:    window "AAAB", countMax=3, size=4, need=1 → valid → maxLen=4
    right=4:    window "AAABA", countMax=4, size=5, need=1 → valid → maxLen=5
    right=5:    window "AAABAB", size=6, need=2 → invalid → shrink to "AABAB"
    right=6:    window "AABABB", size=6, need=2 → invalid → shrink to "ABABB"

    Final maxLen = 5

## Pitfalls

- **Updating `countMax` on shrink.** Tempting but unnecessary — and would
  cost O(26) per shrink, defeating the linear bound. Trust the invariant.
- **Wrong condition direction.** The check is `size - countMax > k`
  (invalid), not `< k`. Off-by-one bugs here are common.
- **Counting all characters vs only uppercase.** The problem guarantees
  uppercase English letters, so a 26-entry array is enough. For ASCII or
  Unicode, switch to a `HashMap<Character, Integer>` instead.

## Connection to previous problems

The pattern is the same as **Longest Substring Without Repeating Characters**
(#3): extend right, validate, shrink left if necessary, record the best.
The only difference is the **validity rule**:

- #3: window must have no duplicate characters
- #424: window's "replacement cost" must fit within k

Once you see the template, the recipe for new sliding-window problems is
just: *"identify the right invariant, then plug it in"*.

## Notes

This is the prototypical *"frequency-based sliding window"* problem. The
same combination of counter array + `countMax` tracker appears in:

- **Permutation in String** (#567) — fixed-size window comparing two counters
- **Find All Anagrams in a String** (#438) — same idea, asking for all matches
- **Minimum Window Substring** (#76) — minimize length while satisfying a
  multi-character constraint

If you can solve this problem, you have the foundations for that family.
