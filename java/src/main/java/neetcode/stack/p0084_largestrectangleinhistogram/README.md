# 84. Largest Rectangle in Histogram

🔗 [LeetCode](https://leetcode.com/problems/largest-rectangle-in-histogram/) | 📺 [NeetCode](https://neetcode.io/problems/largest-rectangle-in-histogram)

**Difficulty:** Hard
**Category:** Stack

## Problem

Given an array `heights` representing the heights of bars in a histogram
(each bar has width 1), return the area of the largest rectangle that can
be formed inside the histogram.

## Key observation

For each bar `i`, the largest rectangle **using `heights[i]` as its height**
extends left until the first bar shorter than `heights[i]`, and right until
the first bar shorter than `heights[i]`. The area is:

    area_i = heights[i] * (right_boundary - left_boundary - 1)

The answer is the maximum over all bars.

## Approach

**Monotonic increasing stack of indices.**

Maintain a stack of indices whose heights are strictly increasing from
bottom to top. When a bar shorter than the stack top is encountered, the
top bar has found its **right boundary** (the current index). Its **left
boundary** is the new top of the stack after popping (the next shorter bar
to the left). Compute its area and update the maximum.

A **sentinel** value of 0 at position `n` ensures the stack is fully
drained at the end without extra post-loop logic.

### Algorithm
