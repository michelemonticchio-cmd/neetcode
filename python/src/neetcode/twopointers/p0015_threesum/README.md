# 15. 3Sum — Python

🔗 [LeetCode](https://leetcode.com/problems/3sum/) | 📺 [NeetCode](https://neetcode.io/problems/three-integer-sum)

**Difficulty:** Medium
**Category:** Two Pointers

## Problem

Given an integer array `nums`, return all unique triplets that sum to zero.

## Approach

**Sort + outer loop + inner two pointers.**

1. Sort `nums` — enables two-pointer search and easy duplicate skipping.
2. For each index `i`, fix `nums[i]` as the first element.
3. Use two pointers `left=i+1`, `right=len-1` to find pairs summing to
   `-nums[i]`.
4. Skip duplicates at both the outer and inner level.

## Complexity

- **Time:** O(n²) — O(n log n) sort + O(n²) search
- **Space:** O(1) extra

## Java vs Python

    // Java
    Arrays.sort(nums);
    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

    # Python
    nums.sort()
    result.append([nums[i], nums[left], nums[right]])

Key differences:
- `nums.sort()` instead of `Arrays.sort(nums)`
- `result.append([...])` instead of `result.add(Arrays.asList(...))`
- `elif` instead of `else if`
- `range(len(nums) - 2)` instead of index-based for loop
