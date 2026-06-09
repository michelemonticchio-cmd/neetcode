# 42. Trapping Rain Water

🔗 [LeetCode](https://leetcode.com/problems/trapping-rain-water/) | 📺 [NeetCode](https://neetcode.io/problems/trapping-rain-water)

**Difficulty:** Hard
**Category:** Two Pointers

## Problem

Given an array of non-negative integers `height` representing an elevation
map where each bar has width 1, return the total amount of water that can
be trapped after raining.

## Approach

**Converging two pointers with running max on each side.**

The water trapped on top of column `i` is determined by:

    water[i] = min(maxLeftOf(i), maxRightOf(i)) - height[i]

The naive approach computes `maxLeft` and `maxRight` arrays in two passes,
giving O(n) time but O(n) space. The two-pointers refinement removes the
extra arrays by leveraging a subtle observation:

> If `height[left] < height[right]`, then we know that **somewhere to the
> right of `left`** there is a bar at least as tall as `height[right]`,
> which is itself ≥ `height[left]`. So the water level above column `left`
> is bounded by `leftMax` (the running max of the left side), regardless
> of what `maxRight` exactly is.

This means: when the left side is the shorter one, we can safely compute
water at `left` using only `leftMax` — the right side is "guaranteed
enough". Symmetric reasoning for the right side.

### Algorithm

1. Initialize `left = 0`, `right = n - 1`, `leftMax = 0`, `rightMax = 0`,
   `total = 0`.
2. While `left < right`:
   - If `height[left] < height[right]`:
     - If `height[left] >= leftMax`, update `leftMax = height[left]`
       (no water — this cell is a new tallest-so-far wall on the left).
     - Else, `total += leftMax - height[left]` (water trapped here).
     - Advance `left++`.
   - Otherwise (symmetric for the right).
3. Return `total`.

## Why the "process the shorter side" rule is correct

When `height[left] < height[right]`, consider column `left`:
- Its left neighborhood's max is exactly `leftMax`.
- Its right neighborhood contains `height[right]`, so `maxRightOf(left) ≥ height[right] > height[left]`.
- Therefore `min(leftMax, maxRightOf(left))` is determined by `leftMax`
  alone (since `maxRightOf(left)` is provably at least `height[left]`).

We can compute `water[left]` correctly without ever knowing `maxRightOf(left)` precisely.
The same logic applies symmetrically: when the right side is shorter, we
can compute `water[right]` knowing only `rightMax`.

## Complexity

- **Time:** O(n) — each cell is visited at most once
- **Space:** O(1) — only five integer variables

This matches the lower bound: every cell must be inspected to know its
height.

## Common pitfalls

- **Mental model "sequence of containers".** A natural first instinct is
  to scan looking for "closed basins" delimited by two walls. This is
  wrong: water can sit on top of intermediate shorter walls if the outer
  walls are taller. Example: `[3, 0, 1, 0, 3]` traps 8 units; the
  middle column of height 1 has 2 units of water above it.
- **Computing the area like in Container With Most Water.** That problem
  asks for a single biggest container; this one asks for the **sum of
  water at every cell**. The two-pointers template is similar, but the
  inner accounting is different.
- **Equality case `height[left] == height[right]`.** Both branches are
  correct; the code above goes to the `else` branch by writing
  `height[left] < height[right]`. Picking the wrong direction in the
  equality case doesn't cause bugs.

## Comparison with the prefix-max approach

A clearer didactic alternative — but with O(n) extra space — is to
precompute two arrays:

    leftMaxArr[i] = max of height[0..i]
    rightMaxArr[i] = max of height[i..n-1]

Then sum `min(leftMaxArr[i], rightMaxArr[i]) - height[i]` over all `i`.
This solution is asymptotically O(n) time but uses O(n) extra space.
The two-pointers version above achieves the same time with O(1) space
by exploiting the fact that one side's running max is always sufficient.

## Notes

Trapping Rain Water is the harder sibling of **Container With Most Water**
(#11): both use converging two pointers with the same decision rule
("process the shorter side"), but the per-step computation differs.

- **Container With Most Water**: track the single maximum area, move the
  shorter side hoping for a taller neighbor.
- **Trapping Rain Water**: accumulate water at every column, with the
  shorter side telling us the binding constraint.

Recognizing this kinship is the mental shortcut: **whenever a problem
boils down to "the worse of two endpoints determines the score, and you
move forward", two pointers + max trackers is the template**.

This pattern also generalizes beyond arrays — e.g., to 2D variants like
**Trapping Rain Water II** (#407), where a priority queue replaces the
two pointers but the "process the lowest boundary" principle is the same.
