# 33. Search in Rotated Sorted Array

🔗 [LeetCode](https://leetcode.com/problems/search-in-rotated-sorted-array/) | 📺 [NeetCode](https://neetcode.io/problems/find-target-in-rotated-sorted-array)

**Difficulty:** Medium
**Category:** Binary Search

## Problem

An ascending sorted array has been rotated between `1` and `n` times.
Given the rotated array `nums` and an integer `target`, return the index of
`target`, or `-1` if it's not present. Must run in O(log n).

## Approach

**Binary search with segment identification.**

A rotated sorted array splits at `mid` into two halves, and **at least one
of them is always fully sorted**. The algorithm:

1. If `nums[mid] == target`, done.
2. Determine which half is sorted by comparing `nums[mid]` with `nums[left]`:
   - `nums[mid] >= nums[left]` → the left half `[left..mid]` is sorted.
   - Otherwise → the right half `[mid..right]` is sorted.
3. Check whether `target` falls within the **sorted half's value range**:
   - If yes, binary search continues in that half.
   - If no, `target` must be in the other half (it can't be in the sorted
     half outside its range), so search there instead.

### Why "at least one half is sorted"

A rotation creates exactly one "break point" where `nums[i] > nums[i+1]`.
Splitting the array anywhere, that break point falls in at most one of the
two halves — the other half is guaranteed contiguous and sorted.

## Complexity

- **Time:** O(log n)
- **Space:** O(1)

## Trace with nums = [4,5,6,7,0,1,2], target = 0

    left=0, right=6, mid=3 → nums[3]=7
    nums[mid]=7 >= nums[left]=4 → left half [4,5,6,7] is sorted
    Is target(0) in [nums[left]=4, nums[mid]=7)? No (0 < 4)
    → target is in the right half → left = 4

    left=4, right=6, mid=5 → nums[5]=1
    nums[mid]=1 >= nums[left]=0 → left half [0,1] (of this sub-range) is sorted
    Is target(0) in [nums[left]=0, nums[mid]=1)? Yes (0 is in [0,1))
    → right = 4

    left=4, right=4, mid=4 → nums[4]=0 == target → return 4 ✅

## Trace with nums = [4,5,6,7,0,1,2], target = 3 (not present)

    left=0, right=6, mid=3 → nums[3]=7
    nums[mid]=7 >= nums[left]=4 → left half [4,5,6,7] sorted
    Is target(3) in [4,7)? No
    → left = 4

    left=4, right=6, mid=5 → nums[5]=1
    nums[mid]=1 >= nums[left]=0 → left half [0,1] sorted
    Is target(3) in [0,1)? No
    → left = 6

    left=6, right=6, mid=6 → nums[6]=2
    nums[mid]=2 >= nums[left]=2 → left half [2] sorted
    Is target(3) in [2,2)? No (empty range)
    → left = 7

    left=7, right=6 → exit loop → return -1 ✅

## Pitfalls

- **Range check boundaries.** `nums[left] <= target < nums[mid]` (left
  inclusive, mid exclusive — mid was already checked for equality at the
  top of the loop) and symmetrically `nums[mid] < target <= nums[right]`.
  Getting `<=` vs `<` wrong on the wrong side causes off-by-one bugs.
- **`nums[mid] >= nums[left]`, not `>`.** When `left == mid` (a single
  remaining element), `nums[mid] >= nums[left]` is trivially true (equal),
  correctly routing into the "left half sorted" branch with an empty range
  check that falls through correctly.
- **This algorithm assumes distinct values.** With duplicates, both halves
  can appear sorted even when the comparison `nums[mid] >= nums[left]`
  doesn't reveal which side the break point is on — see #81 for the
  duplicate-handling variant, which degrades to O(n) in the worst case.

## Connection to Find Minimum in Rotated Sorted Array (#153)

Both problems exploit the same "rotated array = two sorted segments"
structure. #153 only needs to find the segment boundary (the minimum);
this problem additionally needs to locate a specific value, requiring the
extra "is target in this sorted half's range?" check at each step.

## Notes

This is one of the most frequently asked binary search variants in
interviews because it combines two skills: recognizing which part of a
"broken" structure is still well-behaved, and applying standard binary
search logic within that part. The general template — *"identify the
sorted half, then binary search as usual within whichever half could
contain the answer"* — generalizes to:

- **Search in Rotated Sorted Array II** (#81) — duplicates allowed.
- **Find Peak Element** (#162) — a different monotonicity argument but
  same "discard half based on a local comparison" idea.
