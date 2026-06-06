# 125. Valid Palindrome

🔗 [LeetCode](https://leetcode.com/problems/valid-palindrome/) | 📺 [NeetCode](https://neetcode.io/problems/is-palindrome)

**Difficulty:** Easy
**Category:** Two Pointers

## Problem

Given a string `s`, return `true` if it reads the same forwards and backwards,
considering only alphanumeric characters and ignoring letter case.

A palindrome is case-insensitive: `"A man, a plan, a canal: Panama"` is a
palindrome (after stripping non-alphanumeric characters: `"amanaplanacanalpanama"`).

## Approach

**Two pointers converging from both ends, with in-place filtering.**

- `i` starts at the left end, `j` at the right end.
- Outer loop runs while `i < j`.
- Two inner loops advance each pointer past any non-alphanumeric character —
  effectively "filtering" on the fly without allocating a new string.
- Once both pointers land on alphanumeric characters, compare them
  case-insensitively. If they differ, the string isn't a palindrome.
- Otherwise, move both pointers one step toward the center and repeat.

When the loop ends (`i >= j`), every matched pair has been validated, so
the string is a palindrome.

## Complexity

- **Time:** O(n) — each character is visited at most twice (once by `i`, once by `j`)
- **Space:** O(1) — only two integer pointers, no auxiliary strings

This is asymptotically optimal: any correct algorithm must inspect every
character at least once.

## Alternative approach

A simpler but more memory-expensive variant pre-processes the string into a
clean lowercase alphanumeric version, then compares with two pointers (or with
`StringBuilder.reverse()`).

    String clean = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
    // then two pointers on `clean`, or
    // check clean.equals(new StringBuilder(clean).reverse().toString())

Asymptotically O(n) time but **O(n) space** for the cleaned string. The
in-place version above keeps the space O(1).

## Java idioms used

- `Character.isLetterOrDigit(ch)` — alphanumeric check on a single char
- `Character.toLowerCase(ch)` — lowercase conversion on a single char
- `s.charAt(i)` — access the i-th character of a String (Strings aren't arrays in Java)
- `s.length()` — length of a String (method, with parentheses, unlike arrays)

## Pitfalls

- **Always put bounds checks first in `&&` chains.** Writing
  `while (i < j && !isLetterOrDigit(s.charAt(i)))` is safer than the reverse:
  short-circuit evaluation guarantees `s.charAt(i)` is only called when `i`
  is in range, avoiding `IndexOutOfBoundsException`.
- **`j--`, not `j++`.** When two pointers converge, the right one must
  decrease. Easy to typo when the left pointer is `i++`.

## Notes

This problem introduces the **converging two-pointers** pattern: instead of
two nested loops over every pair (O(n²)), have two indices move toward each
other so each step processes one element. The same template appears in:

- **Two Sum II** (#167) — converging pointers on a sorted array
- **3Sum** (#15) — outer loop + converging pair inside
- **Container With Most Water** (#11) — converging pointers maximizing area
- **Trapping Rain Water** (#42) — converging with auxiliary max trackers

The "skip-while-filtering" trick (inner whiles that advance until a valid
position is reached) is also broadly reusable whenever the input contains
characters/items you want to ignore without preallocating a cleaned copy.
```
