# 739. Daily Temperatures

🔗 [LeetCode](https://leetcode.com/problems/daily-temperatures/) | 📺 [NeetCode](https://neetcode.io/problems/daily-temperatures)

**Difficulty:** Medium
**Category:** Stack

## Problem

Given an array `temperatures`, return an array `result` where `result[i]`
is the number of days after day `i` until a warmer temperature. If no such
day exists, `result[i] = 0`.

## Approach

**Monotonic decreasing stack of indices.**

Maintain a stack of day indices whose "next warmer day" has not yet been
found. The stack is kept in **monotonically decreasing order of temperature**:
the bottom has the highest temperature, the top the lowest.

For each day `i`:
1. While the stack is non-empty and `temperatures[stack.peek()] < temperatures[i]`:
   - The day at the top has found its answer today.
   - Pop it and set `result[idx] = i - idx`.
2. Push `i` onto the stack.

Days remaining on the stack at the end have no warmer future day →
`result[idx] = 0` (already the default).

### Why indices and not temperatures?

The answer for a popped day is the **difference of indices** (`i - idx`),
not a temperature comparison. Storing indices lets us compute this in O(1).

## Complexity

- **Time:** O(n) — each index is pushed once and popped at most once
- **Space:** O(n) — worst case (strictly decreasing temperatures) all
  indices remain on the stack until the end

## Trace with [73, 74, 75, 71, 69, 72, 76, 73]

    i=0 T=73: stack=[]      → push(0).          stack=[0]
    i=1 T=74: T[0]=73 < 74 → result[0]=1, pop.  stack=[1]
    i=2 T=75: T[1]=74 < 75 → result[1]=1, pop.  stack=[2]
    i=3 T=71: T[2]=75 > 71 → push(3).           stack=[2,3]
    i=4 T=69: T[3]=71 > 69 → push(4).           stack=[2,3,4]
    i=5 T=72: T[4]=69 < 72 → result[4]=1, pop.
              T[3]=71 < 72 → result[3]=2, pop.
              T[2]=75 > 72 → push(5).            stack=[2,5]
    i=6 T=76: T[5]=72 < 76 → result[5]=1, pop.
              T[2]=75 < 76 → result[2]=4, pop.
              stack empty  → push(6).            stack=[6]
    i=7 T=73: T[6]=76 > 73 → push(7).           stack=[6,7]

    Remaining: indices 6,7 → result[6]=result[7]=0

    Output: [1, 1, 4, 2, 1, 1, 0, 0] ✅

## Pitfalls

- **Storing temperatures instead of indices.** You need indices to compute
  the number of days waited (`i - idx`) and to know which `result` cell
  to update.
- **Using `<=` instead of `<` in the while condition.** With `<`, equal
  temperatures don't trigger a pop — correct, because an equal temperature
  is not a *warmer* day. With `<=` you'd pop equal-temperature days
  prematurely.
- **Forgetting default 0.** Java initializes `int[]` to 0, so days with
  no warmer future are handled automatically without extra code.

## Connection to Sliding Window Maximum (#239)

Both problems use a monotonic structure on indices:
- **Sliding Window Maximum**: monotonic *decreasing* deque, front = current
  maximum, pop from back when adding a larger element.
- **Daily Temperatures**: monotonic *decreasing* stack, pop when the
  current element is larger than the top — giving each popped element its
  answer.

The common thread: *"When does element X get resolved? When the first
element larger than X arrives."* The monotonic structure defers resolution
until that trigger appears.

## Notes

Daily Temperatures is the canonical **"next greater element"** problem.
The monotonic stack is the standard tool for this family:

- **Next Greater Element I & II** (#496, #503) — same pattern, circular array.
- **Largest Rectangle in Histogram** (#84) — monotonic stack for "previous
  and next smaller element".
- **Trapping Rain Water** (#42) — conceptually related; the stack variant
  of that solution also processes "waiting" bars.

Mental model: *"Push indices onto a stack while waiting for something
better. When something better arrives, resolve all waiting indices that
it satisfies."*
