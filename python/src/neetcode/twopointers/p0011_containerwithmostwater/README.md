# 11. Container With Most Water — Python

🔗 [LeetCode](https://leetcode.com/problems/container-with-most-water/) | 📺 [NeetCode](https://neetcode.io/problems/max-water-container)

**Difficulty:** Medium
**Category:** Two Pointers

## Problem

Given an array `heights` where each element represents the height of a
vertical bar, find two bars that form the container holding the most water.

    area = min(heights[left], heights[right]) * (right - left)

## Approach

**Converging two pointers — always move the shorter bar.**

The area is bounded by the shorter of the two bars. Moving the taller bar
can never improve the area (the height cap stays the same, width shrinks).
Moving the shorter bar gives a chance to find a taller neighbor that
compensates for the lost width.

## Complexity

- **Time:** O(n)
- **Space:** O(1)

## Java vs Python

    // Java
    int maxArea = 0;
    while (left < right) {
        int area = Math.min(heights[left], heights[right]) * (right - left);
        maxArea = Math.max(maxArea, area);
        if (heights[left] < heights[right]) left++;
        else right--;
    }

    # Python
    maxA = 0
    while left < right:
        area = min(heights[left], heights[right]) * (right - left)
        maxA = max(maxA, area)
        if heights[left] < heights[right]:
            left += 1
        else:
            right -= 1

Key differences:
- `min()` and `max()` are built-in functions, no `Math.` prefix needed
- `left += 1` instead of `left++` (Python has no `++` operator)
