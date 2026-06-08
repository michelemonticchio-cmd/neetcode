# 11. Container With Most Water

🔗 [LeetCode](https://leetcode.com/problems/container-with-most-water/) | 📺 [NeetCode](https://neetcode.io/problems/max-water-container)

**Difficulty:** Medium
**Category:** Two Pointers

## Problem

Given an integer array `heights` where each element represents the height of
a vertical line on the x-axis, find two lines that, together with the x-axis,
form a container holding the most water.

The area of the container formed by indices `i` and `j` is:

    area(i, j) = min(heights[i], heights[j]) * (j - i)

Return the maximum area achievable.

## Approach

**Converging two pointers, moving the shorter side.**

Start with `left = 0` and `right = n - 1` (the widest possible container).
At each step:

1. Compute the area bounded by `(left, right)`:
   `area = min(heights[left], heights[right]) * (right - left)`
2. Update the running maximum.
3. **Move the pointer with the shorter line** by one step toward the other.

The greedy move is correct because the area is **capped by the shorter side**.
Moving the taller side would only shrink the width without any chance of
raising the height cap (it's still bounded by the shorter side). Moving the
shorter side, on the other hand, gives at least a chance to find a taller
neighbor that more than compensates for the lost width.

## Why moving the shorter side is the optimal choice

Suppose `heights[left] < heights[right]`. Consider what happens if we kept
`left` fixed and moved `right` to the left:

- The width decreases by 1.
- The height is still capped by `heights[left]` (since `right`'s line is
  taller than `left`'s, replacing it with any value just changes whether
  the new `right` is still ≥ `left` — but the cap is still `heights[left]`).

So the area can only stay the same or shrink — never grow. Therefore there's
no point keeping the shorter side fixed: every container with the current
shorter side as one endpoint has already been "beaten" by the current area
(which uses the widest possible width). We can safely move past it.

This argument shows that **every pair we discard is dominated by a pair we
already considered**, so the linear sweep is correct.

## Complexity

- **Time:** O(n) — each pointer moves at most n times
- **Space:** O(1) — three integer variables

This is asymptotically optimal: any solution must inspect each element at
least once.

## Pitfalls

- **Don't move both pointers at once.** Moving both would skip pairs that
  could be the optimum.
- **Edge case `heights[left] == heights[right]`.** Moving either side is
  correct; the area can't improve unless the new line is strictly taller
  than the current cap. The code above moves `right` in this case (via
  the `else` branch), which is fine.

## Comparison with the brute force

A naïve solution tries every pair `(i, j)` in O(n²) time. For each pair it
computes the area and tracks the maximum. The two-pointers refinement avoids
all pairs whose width is provably dominated, dropping the cost to O(n)
without losing any candidate.

This is a textbook example of how a **greedy elimination argument** can turn
a quadratic search into a linear one — a recurring trick in array problems
once a sorting or directional structure is available (here, the implicit
"shrinking window" structure).

## Notes

Container With Most Water is a clean illustration of the **decision rule for
converging two pointers**: at each step, ask which side, if kept fixed, can
never improve the answer — and move the other one. The same reasoning
appears in:

- **Trapping Rain Water** (#42) — converging pointers with auxiliary max
  trackers; same "move the shorter side" intuition, more bookkeeping.
- **Most Water After Removing Lines** (variations and follow-ups).

The mental shortcut: **when the score is bounded by the worse of two
endpoints, move the worse endpoint**.
