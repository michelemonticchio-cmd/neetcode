# 153. Find Minimum in Rotated Sorted Array

🔗 [LeetCode](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) | 📺 [NeetCode](https://neetcode.io/problems/find-minimum-in-rotated-sorted-array)

**Difficulty:** Medium
**Category:** Binary Search

## Problem

An array originally sorted in ascending order has been rotated between `1`
and `n` times. Return the minimum element in O(log n) time.

Example: `[0,1,2,4,5,6,7]` rotated 4 times becomes `[4,5,6,7,0,1,2]`.

## Approach

**Binary search on the two-segment structure.**

A rotated sorted array splits into two ascending segments, where every
element of the first segment is greater than every element of the second:

    [4, 5, 6, 7, 0, 1, 2]
     |--- segment A ---|--- segment B ---|
     (all values >)     (all values <)

The minimum is always the **first element of segment B** — or the single
element of the whole array if it isn't rotated at all (segment B is empty).

### Deciding which segment `mid` is in

Compare `nums[mid]` with `nums[right]`:

- **`nums[mid] > nums[right]`**: `mid` is in segment A. Since segment B
  (containing the minimum) lies entirely to the right, discard `mid` and
  everything left of it: `left = mid + 1`.
- **`nums[mid] <= nums[right]`**: `mid` is in segment B (or the array isn't
  rotated). The minimum is at `mid` or to its left. **Don't discard `mid`**:
  `right = mid`.

### Loop condition: `left < right`, not `left <= right`

Because `right = mid` (not `mid - 1`), using `<=` could loop forever if
`left == right == mid` repeatedly. With `left < right`, the interval
shrinks every iteration and the loop ends exactly when `left == right`,
which points at the minimum.

## Complexity

- **Time:** O(log n)
- **Space:** O(1)

## Trace with nums = [4, 5, 6, 7, 0, 1, 2]

    left=0, right=6
    mid=3: nums[3]=7 > nums[6]=2 → left = 4

    left=4, right=6
    mid=5: nums[5]=1 <= nums[6]=2 → right = 5

    left=4, right=5
    mid=4: nums[4]=0 <= nums[5]=1 → right = 4

    left=4, right=4 → loop ends

    return nums[4] = 0 ✅

## Trace with nums = [3, 1, 2] (rotated once)

    left=0, right=2
    mid=1: nums[1]=1 <= nums[2]=2 → right = 1

    left=0, right=1
    mid=0: nums[0]=3 > nums[1]=1 → left = 1

    left=1, right=1 → loop ends

    return nums[1] = 1 ✅

## Pitfalls

- **Comparing with `nums[left]` instead of `nums[right]`.** Both can work
  with adjusted logic, but comparing with `nums[right]` makes the two cases
  cleanly map to "discard left part" vs "keep mid". Mixing conventions
  mid-algorithm is a common source of bugs.
- **`right = mid - 1` instead of `right = mid`.** This would incorrectly
  exclude `mid` from consideration even when `mid` could be the answer.
- **`left <= right` with `right = mid`.** Can cause an infinite loop when
  `left == right` and the condition keeps setting `right = mid = left`.

## Why a brute-force scan fails the complexity requirement

Scanning for the first index where `nums[i] < nums[i-1]` finds the answer
correctly but is O(n) — it doesn't exploit the sorted structure of each
segment. The problem explicitly requires O(log n), which is only achievable
by discarding half the search space at each step.

## Notes

This problem is part of a family of "binary search on rotated arrays":

- **Search in Rotated Sorted Array** (#33) — find a target value (not just
  the minimum) using the same segment-identification idea, with an extra
  comparison against `nums[left]`.
- **Find Minimum in Rotated Sorted Array II** (#154) — same problem but
  with duplicates allowed, which breaks the clean O(log n) guarantee in
  the worst case (degrades to O(n)).

The mental model: *"Even when an array isn't fully sorted, if you can
determine in O(1) which half a property belongs to, binary search still
applies."*
