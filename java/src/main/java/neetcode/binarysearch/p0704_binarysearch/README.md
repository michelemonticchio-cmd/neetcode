# 704. Binary Search

🔗 [LeetCode](https://leetcode.com/problems/binary-search/) | 📺 [NeetCode](https://neetcode.io/problems/binary-search)

**Difficulty:** Easy
**Category:** Binary Search

## Problem

Given a sorted array of distinct integers `nums` and an integer `target`,
return the index of `target` if it exists, otherwise `-1`.

**Must run in O(log n) time.**

## Approach

**Classic binary search with an interval [left, right].**

Maintain two pointers `left` and `right` delimiting the range of indices
that could still contain `target`. At each step:

1. Compute `mid`, the middle index of `[left, right]`.
2. Compare `nums[mid]` with `target`:
   - **Equal** → found, return `mid`.
   - **`nums[mid] < target`** → target must be in the right half →
     `left = mid + 1`.
   - **`nums[mid] > target`** → target must be in the left half →
     `right = mid - 1`.
3. Repeat while `left <= right`.

If the loop ends without finding a match, `left > right` means the
interval is empty — target doesn't exist.

### Avoiding overflow

`mid = (left + right) / 2` can overflow for very large arrays if
`left + right > Integer.MAX_VALUE`. The safer form:

    mid = left + (right - left) / 2

is mathematically equivalent and avoids the intermediate overflow.

## Complexity

- **Time:** O(log n) — the search interval halves every iteration
- **Space:** O(1) — only a few integer variables

This is optimal: any algorithm that can only compare elements needs
Ω(log n) comparisons to distinguish among n possibilities (information-
theoretic lower bound).

## Trace with nums = [-1, 0, 3, 5, 9, 12], target = 9

    left=0, right=5: mid=2, nums[2]=3 < 9 → left=3
    left=3, right=5: mid=4, nums[4]=9 == 9 → return 4 ✅

## Trace with nums = [-1, 0, 3, 5, 9, 12], target = 2 (not present)

    left=0, right=5: mid=2, nums[2]=3 > 2 → right=1
    left=0, right=1: mid=0, nums[0]=-1 < 2 → left=1
    left=1, right=1: mid=1, nums[1]=0 < 2 → left=2
    left=2, right=1: left > right → exit loop → return -1 ✅

## Pitfalls

- **`left <= right`, not `left < right`.** With `<`, a single-element
  interval (`left == right`) would never be checked, missing the target
  if it's the last remaining candidate.
- **`mid = (left+right)/2` overflow.** Rarely an issue for typical
  constraints, but `left + (right-left)/2` is the safer idiom and worth
  using by default.
- **Off-by-one in bounds update.** After comparing, move to `mid+1` or
  `mid-1` — never re-include `mid` itself, or the loop may never terminate.

## Notes

This is the foundational template for the entire Binary Search category.
Variants you'll see build on this skeleton by changing:

- **What you're searching for** — exact match, leftmost/rightmost occurrence,
  insertion point, etc.
- **What "sorted" means** — a 2D matrix (#74), a rotated array (#33, #153),
  or an implicit search space defined by a monotonic condition rather than
  an explicit array (#875 Koko Eating Bananas).

The mental model: *"If I can answer 'is the target/condition to the left or
right of this midpoint?' in O(1), I can binary search in O(log n)."*
