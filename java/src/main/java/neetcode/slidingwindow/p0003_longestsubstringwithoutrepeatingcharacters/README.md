# 3. Longest Substring Without Repeating Characters

🔗 [LeetCode](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | 📺 [NeetCode](https://neetcode.io/problems/longest-substring-without-duplicates)

**Difficulty:** Medium
**Category:** Sliding Window

## Problem

Given a string `s`, return the length of the longest substring that contains
no duplicate characters. A substring is a *contiguous* sequence of characters
within a string.

## Approach

**Variable-size sliding window with a HashSet.**

Maintain a window `[left, right]` representing the current candidate
substring, with the invariant that **the window never contains duplicate
characters**. A `HashSet<Character>` mirrors the contents of the window for
O(1) duplicate checks.

Each iteration:
1. Read the character `c = s.charAt(right)`.
2. **Shrink from the left** while `c` is already in the window: remove
   `s.charAt(left)` from the set and advance `left++`. This restores the
   invariant.
3. Add `c` to the window.
4. Update the answer with `right - left + 1`.

## Why this runs in O(n)

The nested `while` inside the `for` looks like O(n²) at first glance, but
each character is **added to the set at most once and removed at most once**
across the entire execution. The total work of all inner loops summed
together is O(n). This kind of bound is called *amortized linear* and is
the hallmark of efficient sliding-window algorithms.

## Complexity

- **Time:** O(n) amortized
- **Space:** O(min(n, k)) where k is the alphabet size. The set never holds
  more distinct characters than the alphabet allows (e.g. ≤ 128 for ASCII).

## Pitfalls

- **Order of `while` and `add`.** Shrink the window *before* adding the new
  character. Adding first and then trying to fix the invariant requires
  removing the just-inserted element, which is awkward and easy to get wrong.
- **Computing window length.** It's `right - left + 1`, not `right - left`.
  When `left == right` the window has one character (length 1), not zero.
- **Returning the substring instead of its length.** The problem asks for
  the length only. If a variant asked for the substring itself, you'd also
  track the best `left` and the best length.

## Connection to Best Time to Buy and Sell Stock

That earlier problem can be seen as a "Sliding Window with a 1-element
left side": the left boundary jumps to the new minimum whenever one is
found, while right advances steadily. Here both boundaries move
independently because the validity condition (no duplicates) is richer.

## Alternative — index-tracking optimization

A common variant uses `Map<Character, Integer>` instead of a set, where the
value is the **last seen index** of each character. When a duplicate is
encountered, you can jump `left` directly to `lastIndex + 1` instead of
removing characters one by one. Same asymptotic complexity, slightly fewer
operations in the worst case, but more error-prone — the set version above
is the cleaner default.

## Notes

This problem is the canonical "variable-size sliding window" template:

> 1. Extend the right boundary by one element.
> 2. While the window violates the invariant, shrink the left boundary.
> 3. Record the answer based on the current valid window.

The same template solves several other problems just by changing the
"invariant" and the "answer":

- **Longest Repeating Character Replacement** (#424) — invariant: at most
  `k` substitutions to make all characters equal.
- **Permutation in String** (#567) — fixed-size window, check anagram match.
- **Minimum Window Substring** (#76) — invariant: window must contain all
  characters of a target string; minimize length instead of maximize.

Recognizing the pattern is half the work: once you see *"longest/shortest
contiguous segment satisfying X"*, you're almost certainly looking at a
sliding window.
