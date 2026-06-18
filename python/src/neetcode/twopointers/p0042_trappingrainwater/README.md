# 42. Trapping Rain Water — Python

🔗 [LeetCode](https://leetcode.com/problems/trapping-rain-water/) | 📺 [NeetCode](https://neetcode.io/problems/trapping-rain-water)

**Difficulty:** Hard
**Category:** Two Pointers

## Problem

Given an array `height` representing an elevation map, return the total
water trapped after raining.

## Key insight

For each column `i`, the water above it is:

    water[i] = min(maxLeft, maxRight) - height[i]

## Approach

**Converging two pointers with running max on each side.**

Always process the side with the shorter current bar:

- If `height[left] < height[right]`: the left side is the bottleneck.
  Update `leftMax` or accumulate water, then advance `left`.
- Otherwise: symmetric on the right side.

This works because when `height[left] < height[right]`, we know
`maxRight >= height[right] > height[left]`, so `leftMax` is the true
binding constraint — no need to know the exact `maxRight`.

## Complexity

- **Time:** O(n)
- **Space:** O(1)

## Java vs Python

    // Java
    if (height[left] < height[right]) {
        if (height[left] >= leftMax) leftMax = height[left];
        else total += leftMax - height[left];
        left++;
    }

    # Python
    if height[left] < height[right]:
        if height[left] >= leftMax:
            leftMax = height[left]
        else:
            total += leftMax - height[left]
        left += 1

Key differences:
- No curly braces — indentation defines blocks
- `left += 1` instead of `left++`
- `leftMax, rightMax = 0, 0` — multiple assignment in one line
