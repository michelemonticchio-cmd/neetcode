# 125. Valid Palindrome — Python

🔗 [LeetCode](https://leetcode.com/problems/valid-palindrome/) | 📺 [NeetCode](https://neetcode.io/problems/is-palindrome)

**Difficulty:** Easy
**Category:** Two Pointers

## Problem

Given a string `s`, return `True` if it is a palindrome considering only
alphanumeric characters and ignoring case.

## Approach A — Clean + reverse (Pythonic)

    cleaned = "".join(c.lower() for c in s if c.isalnum())
    return cleaned == cleaned[::-1]

1. Generator expression filters non-alphanumeric and lowercases in one pass.
2. `[::-1]` reverses the string (Python slicing with step -1).
3. Compare original cleaned string with its reverse.

**Time:** O(n) — **Space:** O(n) for the cleaned string.

## Approach B — Two pointers (O(1) space)

Converging pointers skip non-alphanumeric characters in place — no
auxiliary string needed. Mirrors the Java solution exactly.

    left, right = 0, len(s) - 1
    while left < right:
        while left < right and not s[left].isalnum():  left += 1
        while left < right and not s[right].isalnum(): right -= 1
        if s[left].lower() != s[right].lower(): return False
        left += 1
        right -= 1
    return True

**Time:** O(n) — **Space:** O(1)

## Python tools used

- `c.isalnum()` — like `Character.isLetterOrDigit(c)` in Java
- `c.lower()` — like `Character.toLowerCase(c)` in Java
- `"".join(...)` — concatenates an iterable of strings
- `s[::-1]` — reverses a string (no Java equivalent, needs StringBuilder)
- Generator expression `(expr for x in iterable if condition)` — lazy
  filter+map in one line

## Java vs Python

    // Java (two pointers)
    while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;
    while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;

    # Python (two pointers)
    while left < right and not s[left].isalnum():  left += 1
    while left < right and not s[right].isalnum(): right -= 1

Key differences:
- `not x.isalnum()` instead of `!Character.isLetterOrDigit(x)`
- `s[i]` instead of `s.charAt(i)`
- `and` instead of `&&`
