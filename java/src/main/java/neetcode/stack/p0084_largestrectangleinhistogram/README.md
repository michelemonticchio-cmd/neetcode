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

    for i from 0 to n (inclusive):
        currentHeight = (i == n) ? 0 : heights[i]
        while stack not empty AND heights[stack.top] > currentHeight:
            height = heights[stack.pop()]
            width  = stack.empty ? i : i - stack.top - 1
            maxArea = max(maxArea, height * width)
        push i
    return maxArea

### Width formula explained

After popping index `top`:
- **Right boundary** = `i` (exclusive, the first bar shorter on the right)
- **Left boundary** = `stack.peek() + 1` (exclusive, the first bar shorter
  on the left, which is still on the stack)
- **Width** = right - left = `i - stack.peek() - 1`
- Special case: if the stack is empty after the pop, there is no shorter
  bar to the left, so the rectangle spans all the way to index 0:
  **width** = `i`

## Complexity

- **Time:** O(n) — each index is pushed once and popped at most once
- **Space:** O(n) — the stack holds at most n+1 indices

## Trace with heights = [2, 1, 5, 6, 2, 3]

    i=0 h=2: stack=[]     → push(0).           stack=[0]
    i=1 h=1: h[0]=2>1 → pop(0): height=2, stack empty → width=1, area=2
             stack=[]     → push(1).           stack=[1]
    i=2 h=5: 1<5          → push(2).           stack=[1,2]
    i=3 h=6: 5<6          → push(3).           stack=[1,2,3]
    i=4 h=2: h[3]=6>2 → pop(3): height=6, peek=2 → width=4-2-1=1, area=6
             h[2]=5>2 → pop(2): height=5, peek=1 → width=4-1-1=2, area=10 ⭐
             h[1]=1<2 → stop. push(4).         stack=[1,4]
    i=5 h=3: 2<3          → push(5).           stack=[1,4,5]
    i=6 h=0 (sentinel):
             h[5]=3>0 → pop(5): height=3, peek=4 → width=6-4-1=1, area=3
             h[4]=2>0 → pop(4): height=2, peek=1 → width=6-1-1=4, area=8
             h[1]=1>0 → pop(1): height=1, stack empty → width=6, area=6
             stack=[] → push(6).

    return maxArea = 10 ✅

## Contrast with Daily Temperatures (#739)

| Aspect | Daily Temperatures | Largest Rectangle |
|---|---|---|
| Stack order | Monotonic **decreasing** | Monotonic **increasing** |
| Pop trigger | Current element is **larger** | Current element is **smaller** |
| What a pop resolves | "Next warmer day" | "Largest rectangle centered here" |
| What we store | Indices waiting for a warmer day | Indices waiting for a left+right boundary |

Both use the same "defer resolution until a trigger arrives" mental model,
just in opposite directions.

## Pitfalls

- **Off-by-one in width.** `i - stack.peek() - 1` is easy to miscount.
  Draw the interval on paper: right boundary is `i` (exclusive), left
  boundary is `stack.peek() + 1` (exclusive). Width = `i - (stack.peek() + 1) = i - stack.peek() - 1`.
- **Not handling the empty stack case.** When the stack is empty after a pop,
  the rectangle spans from 0 to `i-1` (width = `i`), not `i - (-1) - 1 = i`.
  Always check `stack.isEmpty()` before calling `stack.peek()`.
- **Forgetting the sentinel.** Without it you need a separate loop after
  the main one to drain the remaining stack. The sentinel at height 0
  triggers all remaining pops naturally.

## Notes

Largest Rectangle in Histogram is one of the hardest stack problems because
it requires reasoning about both left and right boundaries simultaneously.
The key insight — *"a bar's maximum rectangle is determined by the nearest
shorter bars on each side"* — connects it to the broader "previous/next
smaller element" family:

- **Trapping Rain Water** (#42) — similar "nearest shorter on each side"
  reasoning, solved with two-pointer instead.
- **Maximal Rectangle** (#85) — extends this problem to a 2D matrix by
  treating each row as a histogram.
- **Sum of Subarray Minimums** (#907) — same monotonic stack idea to count
  subarrays where a bar is the minimum.
