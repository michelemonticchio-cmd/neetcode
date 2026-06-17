# 217. Contains Duplicate — Python

🔗 [LeetCode](https://leetcode.com/problems/contains-duplicate/) | 📺 [NeetCode](https://neetcode.io/problems/duplicate-integer)

**Difficulty:** Easy
**Category:** Arrays & Hashing

## Problem

Given an integer array `nums`, return `True` if any value appears more
than once, `False` otherwise.

## Approach

**Set length comparison.**

A Python `set` automatically discards duplicates. If converting `nums` to
a set produces a shorter collection, at least one value appeared more than
once.

    return len(nums) != len(set(nums))

## Alternative — explicit HashSet (closer to the Java solution)

```python
seen = set()
for n in nums:
    if n in seen:
        return True
    seen.add(n)
return False
```

Same O(n) time and space, but short-circuits on the first duplicate found
instead of always scanning the entire list.

## Complexity

- **Time:** O(n) — building the set requires one full pass
- **Space:** O(n) — the set holds at most n distinct elements

## Java vs Python

    // Java
    Set<Integer> seen = new HashSet<>();
    for (int n : nums) {
        if (seen.contains(n)) return true;
        seen.add(n);
    }
    return false;

    # Python one-liner
    return len(nums) != len(set(nums))

Key differences: no type declarations, `in` instead of `.contains()`,
`True/False` capitalized, no curly braces — indentation is mandatory.
