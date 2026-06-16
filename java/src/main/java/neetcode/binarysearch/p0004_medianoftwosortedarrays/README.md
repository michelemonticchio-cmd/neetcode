# 4. Median of Two Sorted Arrays

🔗 [LeetCode](https://leetcode.com/problems/median-of-two-sorted-arrays/) | 📺 [NeetCode](https://neetcode.io/problems/median-of-two-sorted-arrays)

**Difficulty:** Hard
**Category:** Binary Search

## Problem

Given two sorted arrays `nums1` (size m) and `nums2` (size n), return the
median of the combined sorted array. Must run in O(log(m + n)).

## Key insight: median = correct partition

The median divides the combined array into two halves of equal size. Instead
of merging, binary search for the right **partition point**: how many elements
of `nums1` belong in the left half. Everything else in the left half comes
from `nums2`.

## Approach

**Binary search on the partition of the shorter array.**

Let `half = (m + n) / 2`. If `i` elements of `nums1` are in the left half,
then `j = half - i` elements of `nums2` are too. The partition is **correct**
when neither left boundary exceeds the other's right boundary:

    nums1[i-1] <= nums2[j]   AND   nums2[j-1] <= nums1[i]

If `nums1[i-1] > nums2[j]`: `i` is too large → `right = i - 1`.
If `nums2[j-1] > nums1[i]`: `i` is too small → `left = i + 1`.

### Reading the median from the correct partition

    nums1Left  = nums1[i-1]  (or -∞ if i == 0)
    nums1Right = nums1[i]    (or +∞ if i == m)
    nums2Left  = nums2[j-1]  (or -∞ if j == 0)
    nums2Right = nums2[j]    (or +∞ if j == n)

- **Odd** total: `min(nums1Right, nums2Right)` — the first element of the right half.
- **Even** total: `(max(nums1Left, nums2Left) + min(nums1Right, nums2Right)) / 2.0`
  — average of the two inner elements.

### Why binary search on the shorter array?

`i` ranges from 0 to `m`. By ensuring `m <= n`, the search space is
O(log m) ≤ O(log(m+n)), and `j = half - i` stays non-negative.

## Complexity

- **Time:** O(log(min(m, n)))
- **Space:** O(1)

## Trace with nums1 = [1, 2], nums2 = [3]

    m=2, n=1, half=1, left=0, right=2

    i=1, j=0:
      nums1Left=1,    nums1Right=2
      nums2Left=-inf, nums2Right=3
      1 <= 3 ✅  -inf <= 2 ✅ → correct partition!
      total=3 (odd) → return min(2, 3) = 2.0 ✅

## Trace with nums1 = [1, 3], nums2 = [2, 4]

    m=2, n=2, half=2, left=0, right=2

    i=1, j=1:
      nums1Left=1, nums1Right=3
      nums2Left=2, nums2Right=4
      1 <= 4 ✅  2 <= 3 ✅ → correct partition!
      total=4 (even) → (max(1,2) + min(3,4)) / 2.0 = (2+3)/2.0 = 2.5 ✅

## Pitfalls

- **Not swapping to ensure nums1 is shorter.** If `m > n`, then `j` could
  go negative. Always binary search on the shorter array.
- **Sentinel values.** When `i == 0`, there's no `nums1[i-1]`; treat it as
  `-∞` so it never violates the partition condition on the left side.
  Symmetrically, `i == m` needs `+∞`.
- **Integer overflow in the even case.** `Math.max(...) + Math.min(...)` can
  overflow if values are near `Integer.MAX_VALUE`. In practice, LeetCode
  constraints keep values small, but `/2.0` (not `/2`) is essential to
  return a `double`.

## Notes

Median of Two Sorted Arrays is widely considered the hardest problem in the
Binary Search category because the "array to search" is implicit: you're
not looking for a value in an array, but for a partition point that satisfies
a two-sided inequality. The leap from "search for a value" to "search for
a structural property" is what makes this problem hard — and what makes it
such a good interview question for senior engineers.

The partition technique generalizes to k-th smallest element in two sorted
arrays, and conceptually to merge sort's merge step when only the median
(not the full merge) is needed.
