# 15. 3Sum

🔗 [LeetCode](https://leetcode.com/problems/3sum/) | 📺 [NeetCode](https://neetcode.io/problems/three-integer-sum)

**Difficulty:** Medium
**Category:** Two Pointers

## Problem

Given an integer array `nums`, return all **unique** triplets
`[nums[i], nums[j], nums[k]]` such that `i`, `j`, `k` are distinct and
`nums[i] + nums[j] + nums[k] == 0`.

The output must not contain duplicate triplets. The triplets and the output
list can be returned in any order.

## Approach

**Sort the array, then reduce to Two Sum II for each fixed element.**

The key observation: once you sort the array, finding two numbers with a
given sum becomes an O(n) two-pointers task (the canonical Two Sum II
pattern). 3Sum is then a natural extension:

1. **Sort** `nums` ascending.
2. **For each index `i`**, fix `nums[i]` as the first element of the triplet.
3. **Use two pointers** `left = i+1`, `right = n-1` to find pairs `(b, c)`
   such that `nums[i] + b + c = 0`, i.e. `b + c = -nums[i]`.
4. **Handle duplicates** at two levels to ensure unique output:
   - Outer loop: skip `i` if `nums[i] == nums[i-1]`.
   - Inner pointers: after a successful match, advance past any duplicates
     of `nums[left]` and `nums[right]` before moving on.

### Early termination

Because the array is sorted, once `nums[i] > 0` no triplet can sum to 0
(all remaining elements are positive), so we can `break` immediately.

## Complexity

- **Time:** O(n²) — sorting is O(n log n); the outer loop runs n times, each
  inner two-pointers scan is O(n), giving O(n²) overall.
- **Space:** O(1) extra (not counting the output list and the space used
  by the sorting algorithm).

This is asymptotically optimal: 3Sum cannot be solved faster than O(n²)
under standard assumptions (a sub-quadratic solution would imply a faster
algorithm for 3SUM, a long-standing open problem in computer science).

## Duplicate handling — the subtle part

There are two distinct sources of duplicate triplets:

1. **Same value of `nums[i]` chosen twice as the fixed element.** Example:
   `nums = [-1, -1, 0, 1]` — without skipping, both indices `i = 0` and
   `i = 1` would produce the same triplet `[-1, 0, 1]`. Solution:
   `if (i > 0 && nums[i] == nums[i-1]) continue;`

2. **Same `(b, c)` pair found from a different starting position inside
   the inner walk.** Example: `nums = [-2, 0, 0, 0, 2]` — after finding
   `[-2, 0, 2]` we must skip the extra zeros, otherwise we'd report it
   multiple times. Solution: after a match, advance `left` past duplicates
   and `right` back past duplicates, then move both by one more.

Both are needed: skipping at only one level leaves the other open.

## Pitfalls

- **Forgetting to sort first.** The two-pointers logic only works on a
  sorted array.
- **Skipping duplicates everywhere.** Skipping `nums[i]` against
  `nums[i-1]` (not `nums[i+1]`) is correct — you want to skip the *second*
  occurrence of a value, not the first.
- **Loop bound.** The outer loop runs up to `n - 2` (need at least two
  elements after `i` for `left` and `right`).

## Notes

3Sum is the prototypical example of the **"reduce by fixing one element"**
template. Many harder problems are solved this way:

- **4Sum** (#18) — two nested outer loops, then Two Sum II inside.
- **3Sum Closest** (#16) — same loop structure, track the closest sum
  instead of an exact match.
- **3Sum Smaller** (#259) — count pairs with `sum < target` inside the
  two-pointers walk.

The mental shortcut: **k-Sum can be reduced to (k-1)-Sum by fixing one
element and recursing**. The base case (k = 2 on a sorted array) is
Two Sum II, which runs in O(n) — so k-Sum runs in O(n^(k-1)) overall.
