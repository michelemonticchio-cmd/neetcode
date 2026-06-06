# 167. Two Sum II — Input Array Is Sorted

🔗 [LeetCode](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) | 📺 [NeetCode](https://neetcode.io/problems/two-integer-sum-ii)

**Difficulty:** Medium
**Category:** Two Pointers

## Problem

Given an array of integers `numbers` already sorted in **non-decreasing** order,
return the **1-indexed** positions of two numbers such that they add up to a
given `target`.

Constraints:
- Exactly one valid solution always exists.
- The same element cannot be used twice (`index1 < index2`).
- **Must use O(1) extra space.**

## Approach

**Converging two pointers on a sorted array.**

Initialize `i` at the leftmost index and `j` at the rightmost. Compute the
current sum `numbers[i] + numbers[j]` and react:

- **Sum equals target** → done; return `[i+1, j+1]` (1-indexed).
- **Sum is too small** → we need a larger number; since the array is sorted,
  larger values are to the right → `i++`.
- **Sum is too large** → we need a smaller number; smaller values are to the
  left → `j--`.

Each iteration moves one pointer by one position, so the two pointers
converge in at most `n - 1` steps.

## Why this works (and why HashMap would be a waste here)

In the classic Two Sum (#1) the array is unsorted, so we need a HashMap to
get O(n) time — at the cost of O(n) space. Here the array is **sorted**,
which gives us a stronger property: when we discard a pair (because its
sum is too small or too big), we discard not just one pair but an entire
"slice" of pairs that would have the same problem. That's why a single
pass with O(1) memory is enough.

Concretely, when `sum < target` and we do `i++`, we're discarding **all**
pairs `(numbers[i], numbers[k])` with `k <= j` — none of them could reach
the target, because they would only get smaller as `k` decreases.

## Complexity

- **Time:** O(n) — at most `n` pointer moves
- **Space:** O(1) — only two integer indices and the output array

This is asymptotically optimal: any algorithm must inspect each element at
least once in the worst case.

## Pitfalls

- **1-indexed output.** The problem returns positions, not Java indices.
  Don't forget to add `+1` to both before returning.
- **Move only one pointer per iteration.** Moving both at once (when the
  sum is not equal) can skip over the correct pair. The single-step
  movement is what guarantees correctness.
- **Comparing single elements to `target` is wrong.** It might be tempting
  to write "if numbers[j] > target then j--" as a shortcut — but the array
  can contain negatives, and even with only positives the comparison should
  always be on the **sum**, never on a single element.

## Notes

This is the canonical example of the **converging two-pointers** pattern,
made possible by the sorted-array precondition. Whenever you see "sorted
input" in a problem statement, ask yourself if two pointers can replace a
HashMap and turn O(n) space into O(1).

Other problems in this category that use the same template:
- **3Sum** (#15) — fix one element, then two-pointers on the rest
- **Container With Most Water** (#11) — converging pointers maximizing area
- **Trapping Rain Water** (#42) — converging with auxiliary max trackers

The mental shortcut to remember: **sorted array + pair/triple search → two pointers**.
