# 875. Koko Eating Bananas

🔗 [LeetCode](https://leetcode.com/problems/koko-eating-bananas/) | 📺 [NeetCode](https://neetcode.io/problems/eating-bananas)

**Difficulty:** Medium
**Category:** Binary Search

## Problem

Given `piles[i]` bananas in pile `i` and `h` hours total, Koko picks a fixed
eating speed `k` (bananas/hour). Each hour she eats `k` bananas from a
single pile (or the remainder, if less than `k`, finishing that pile —
she cannot move to another pile the same hour). Return the minimum `k`
such that she can finish all piles within `h` hours.

## Approach

**Binary search on the answer.**

This is a different flavor of binary search than #704 or #74: there's no
array to search — instead, `k` itself is the value we binary search over.

### Step 1: hours needed for a given k

For a pile of `p` bananas at speed `k`, the hours needed are `ceil(p/k)`,
computed with integer arithmetic as `(p + k - 1) / k`. Summing over all
piles gives `totalHours(k)`.

### Step 2: monotonicity

`totalHours(k)` is **monotonically decreasing** in `k`: increasing the
eating speed never increases the time needed. This monotonicity is what
makes binary search valid — we're looking for the boundary between
"k too slow" (`totalHours(k) > h`) and "k fast enough" (`totalHours(k) <= h`).

### Step 3: search space

- `left = 1` — the slowest possible speed.
- `right = max(piles)` — eating faster than the largest pile is wasted;
  any pile finishes in 1 hour at that speed regardless.

### Step 4: the search

    while left <= right:
        mid = (left + right) / 2
        if totalHours(mid) <= h:
            right = mid - 1   # mid works; look for something smaller
        else:
            left = mid + 1    # mid too slow; look for something faster
    return left

When the loop ends, `left` is exactly the smallest `k` for which
`totalHours(k) <= h`.

## Why `return left` and not "return when found"

Unlike #704 (where we return immediately on finding the target), here every
`k` in `[left, right]` is a *candidate* to check, and many values may satisfy
`totalHours(k) <= h`. We want the **smallest** one. So when `mid` works, we
don't stop — we narrow `right` to search for something even smaller.
`left` converges to that minimum boundary.

## Complexity

- **Time:** O(n · log(max(piles))) — O(log(max)) binary search iterations,
  each computing `totalHours` in O(n)
- **Space:** O(1)

## Trace with piles = [3, 6, 7, 11], h = 8

    left=1, right=11
    mid=6: hours = ceil(3/6)+ceil(6/6)+ceil(7/6)+ceil(11/6) = 1+1+2+2=6
           6 <= 8 → right=5
    left=1, right=5
    mid=3: hours = 1+2+3+4 = 10
           10 > 8 → left=4
    left=4, right=5
    mid=4: hours = 1+2+2+3 = 8
           8 <= 8 → right=3
    left=4, right=3 → exit loop

    return left = 4 ✅

## Pitfalls

- **Ceiling division.** `(p + k - 1) / k` is the integer-only equivalent of
  `Math.ceil((double) p / k)`. Forgetting the `+ k - 1` truncates instead of
  rounding up, giving wrong (too-low) hour counts.
- **Search bounds.** `right = max(piles)`, not `Arrays.stream(piles).sum()`
  or some arbitrary large number — a tighter bound means fewer iterations,
  though correctness holds either way as long as `right` is an upper bound
  on the answer.
- **Off-by-one in the final return.** `return left` (not `right` or `mid`)
  is correct because `left` is the first value where the condition
  `totalHours(k) <= h` becomes true, by construction of the loop.

## Notes

This is the canonical **"binary search the answer"** template:

> *"If I can check 'does value X satisfy the condition?' in some time
> T(n), and the condition is monotonic in X, I can binary search over X
> in O(T(n) · log(range))."*

The same template applies to:

- **Capacity to Ship Packages Within D Days** (#1011) — same structure,
  "ship capacity" instead of "eating speed".
- **Split Array Largest Sum** (#410) — minimize the largest subarray sum
  given a number of splits.
- **Find the Smallest Divisor Given a Threshold** (#1283) — nearly identical
  to this problem with a different cost function.

Recognizing "minimize/maximize X such that condition(X) holds, and
condition is monotonic in X" is the trigger for this pattern.
