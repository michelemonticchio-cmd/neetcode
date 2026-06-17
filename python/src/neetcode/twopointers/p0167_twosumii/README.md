# 167. Two Sum II — Python

🔗 [LeetCode](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) | 📺 [NeetCode](https://neetcode.io/problems/two-integer-sum-ii)

**Difficulty:** Medium
**Category:** Two Pointers

## Problem

Given a sorted array `numbers`, return the 1-indexed positions of two
numbers that sum to `target`. Must use O(1) extra space.

## Approach

**Converging two pointers.**

Start with `left=0` and `right=len-1`. At each step:
- Sum too small → `left += 1` (need a larger number)
- Sum too large → `right -= 1` (need a smaller number)
- Sum equals target → return `[left+1, right+1]` (1-indexed)

The sorted order guarantees we never miss the solution.

## Complexity

- **Time:** O(n)
- **Space:** O(1)

## Java vs Python

    // Java
    int left = 0, right = numbers.length - 1;
    while (left < right) {
        int s = numbers[left] + numbers[right];
        if (s == target) return new int[]{left+1, right+1};
        else if (s < target) left++;
        else right--;
    }

    # Python
    left, right = 0, len(numbers) - 1
    while left < right:
        s = numbers[left] + numbers[right]
        if s == target:
            return [left + 1, right + 1]
        elif s < target:
            left += 1
        else:
            right -= 1

Key differences:
- `left, right = 0, len(numbers) - 1` — multiple assignment in one line
- `elif` instead of `else if`
- `len(numbers)` instead of `numbers.length`
- `return []` instead of `return new int[]{}`
