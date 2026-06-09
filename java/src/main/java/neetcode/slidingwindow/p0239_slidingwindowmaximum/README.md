# 239. Sliding Window Maximum

🔗 [LeetCode](https://leetcode.com/problems/sliding-window-maximum/) | 📺 [NeetCode](https://neetcode.io/problems/sliding-window-maximum)

**Difficulty:** Hard
**Category:** Sliding Window

## Problem

Given an integer array `nums` and a window size `k`, return an array of the
maximum value in each window as it slides from left to right. The output
has `n - k + 1` elements.

## Approach

**Monotonic decreasing deque of indices.**

Maintain a `Deque<Integer>` storing array *indices* (not values) such that
the corresponding values are always in **strictly decreasing** order from
front to back. This invariant guarantees:

> The front of the deque is always the index of the **maximum** in the
> current window.

### Per-step operations

For each new `right`:

1. **Remove from back** (maintain monotonicity): while the back index has
   a value ≤ `nums[right]`, pop it. Those elements can never become the
   window maximum while `nums[right]` is in the window.
2. **Add `right` to back.**
3. **Remove from front** (evict expired elements): if the front index is
   `< left`, it has slid out of the window — pop it.
4. **Record result**: once the window is full (`right >= k - 1`), append
   `nums[deque.peekFirst()]` to the output and advance `left`.

### Why indices and not values?

Storing indices lets us check whether a front element has expired
(`index < left`) in O(1). If we stored values we'd have no way to know
which window position they belong to.

## Complexity

- **Time:** O(n) — each index is pushed and popped from the deque at most
  once across the entire loop (amortized linear, same argument as
  Longest Substring Without Repeating Characters).
- **Space:** O(k) — the deque holds at most k indices at any time.

## Trace with nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3

    right=0: deque=[0]                  window not full yet
    right=1: 3>1 → pop 0; deque=[1]    window not full yet
    right=2: -1<3 → keep; deque=[1,2]  window [1,3,-1]  → max=nums[1]=3
    right=3: -3<-1→keep; deque=[1,2,3] window [3,-1,-3] → max=nums[1]=3
    right=4: 5>all → pop 3,2,1; deque=[4] window [-1,-3,5] → max=nums[4]=5
    right=5: 3<5→keep; deque=[4,5]     window [-3,5,3]  → max=nums[4]=5
    right=6: 6>3,5→pop 5,4; deque=[6]  window [5,3,6]   → max=nums[6]=6
    right=7: 7>6→pop 6; deque=[7]      window [3,6,7]   → max=nums[7]=7

    Output: [3, 3, 5, 5, 6, 7] ✅

## Pitfalls

- **Storing values instead of indices.** You need indices to detect when
  the front element has left the window.
- **Wrong eviction condition.** Evict the front when `deque.peekFirst() < left`,
  not `<= left`. The element at `left` is still inside the current window.
- **Recording the result too early.** Only write to `result` when
  `right >= k - 1` — before that the window isn't fully formed yet.
- **Using `<` vs `<=` in the back-removal condition.** Using `<` (strict)
  means equal values are kept — both are valid choices but have different
  behavior when duplicates exist. Using `<=` keeps only the rightmost of
  equal values (slightly cleaner for tie-breaking).

## Why the deque is O(n) despite the inner while

Each index is added to the deque exactly once and removed at most once
(either from the back during the monotonicity cleanup, or from the front
during eviction). The total number of operations across all iterations of
the inner `while` is therefore O(n), not O(n) per outer iteration —
this is the same amortized argument seen in Longest Substring Without
Repeating Characters (#3) and Longest Consecutive Sequence (#128).

## Notes

The **monotonic deque** pattern — maintaining a deque whose values are
ordered so that the optimal element is always at one end — is a powerful
technique that appears in:

- **Jump Game VI** (#1696) — maximum score with bounded jumps (monotonic
  deque for DP transitions).
- **Shortest Subarray with Sum at Least K** (#862) — monotonic deque on
  prefix sums.
- **Maximum Sum of Subarray of Size K with Constraints** (various contests).

The key mental model: *"If element A enters the window after element B and
A ≥ B, then B can never be the answer for any future window — discard it."*
This "dominance" argument is what makes the deque monotonic and the
algorithm linear.
