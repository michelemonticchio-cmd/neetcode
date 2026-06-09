# 76. Minimum Window Substring

🔗 [LeetCode](https://leetcode.com/problems/minimum-window-substring/) | 📺 [NeetCode](https://neetcode.io/problems/minimum-window-with-characters)

**Difficulty:** Hard
**Category:** Sliding Window

## Problem

Given two strings `s` and `t`, return the shortest substring of `s` that
contains every character of `t` (including duplicates). Return `""` if no
such substring exists.

## Approach

**Variable-size sliding window with two frequency maps and a `formed` counter.**

### Setup

- `need`: frequency map of `t` — how many of each character we must include.
- `window`: frequency map of the current window in `s`.
- `required`: number of distinct characters in `t` that must be satisfied
  (= `need.size()`).
- `formed`: how many of those distinct characters are currently satisfied
  in the window (i.e. `window.get(c) >= need.get(c)`).

### Main loop

Expand `right` one step at a time:
1. Add `s.charAt(right)` to the window.
2. If its new count exactly matches `need`, increment `formed`.
3. While `formed == required` (window is valid):
   - Record the window if it's the shortest seen so far.
   - Remove `s.charAt(left)` from the window.
   - If that removal breaks a satisfied character, decrement `formed`.
   - Advance `left++`.

The inner while loop shrinks the window as much as possible while keeping
it valid, then the outer loop expands it again. This guarantees every
valid window is minimized before we move on.

### Why `formed` instead of comparing full maps

Comparing two `HashMap`s directly would be O(|t|) per step. The `formed`
counter reduces this to O(1): we only increment or decrement it when a
character's count crosses the exact threshold defined by `need`. This
keeps the overall complexity linear.

## Complexity

- **Time:** O(|s| + |t|) — each character of `s` is added and removed
  from the window at most once; building `need` takes O(|t|).
- **Space:** O(|t|) — the two maps together hold at most O(|alphabet|)
  entries, bounded by |t|.

## Trace with s = "OUZODYXAZV", t = "XYZ"

    need = {X:1, Y:1, Z:1}, required = 3

    right=0 'O': window={O:1}, formed=0
    right=1 'U': window={O:1,U:1}, formed=0
    right=2 'Z': window={...,Z:1}, formed=1
    right=3 'O': formed=1
    right=4 'D': formed=1
    right=5 'Y': window={...,Y:1}, formed=2
    right=6 'X': window={...,X:1}, formed=3 ← valid!
      Shrink: left=0 'O' → remove, formed still 3, windowLen=7 "OUZODYX"? 
      Actually window is now valid → record minLen=7, minLeft=0
      continue shrinking...
      left=1 'U' → remove, still valid, record minLen=6, minLeft=1
      left=2 'Z' → remove → formed=2, stop shrinking
    right=7 'A': formed=2
    right=8 'Z': window={...,Z:1}, formed=3 ← valid!
      Shrink: left=3 'O' → remove, still valid, record minLen=6 (no improvement)
      left=4 'D' → remove, still valid
      left=5 'Y' → remove → formed=2, stop. Best so far: minLen=4 "YXAZ"
    right=9 'V': formed=2, no more valid windows

    Return s.substring(5, 5+4) = "YXAZ" ✅

## Pitfalls

- **`window.get(c).equals(need.get(c))` not `==`.**
  `Integer` objects must be compared with `.equals()`, not `==`, because
  `==` checks reference equality. For small values Java caches Integer
  objects so `==` sometimes works, but it is unreliable above ~127. Always
  use `.equals()` for `Integer` comparisons.
- **Decrement `formed` only when count drops *below* `need`.**
  If `need.get(c) = 1` and `window.get(c)` goes from 2 to 1, the character
  is still satisfied — don't decrement `formed`. Only decrement when the
  count falls strictly below the requirement.
- **Return `""` when no valid window exists.**
  Check `minLen == Integer.MAX_VALUE` before calling `s.substring(...)`.

## Comparison with Permutation in String (#567)

| Aspect | Permutation in String | Minimum Window Substring |
|---|---|---|
| Window size | Fixed (`s1.length()`) | Variable |
| Validity condition | Exact frequency match | Window counts ≥ t counts |
| Goal | Find any valid window | Find the *shortest* valid window |
| Shrink when | Never (fixed size) | Window is valid (minimize) |
| Data structure | `int[26]` arrays | `HashMap` (handles any chars) |

Both use the same sliding-window skeleton; the differences are only in the
validity rule and when to shrink.

## Notes

This is one of the hardest sliding-window problems because it combines:
1. A variable-size window (like Longest Substring Without Repeating Characters)
2. A multi-character satisfaction condition tracked with `formed`
3. Shrinking to minimize (instead of shrinking to restore validity)

Once you see the `need / window / formed / required` scaffold, it applies
to a whole family of "find a window satisfying a multi-set condition" problems.
