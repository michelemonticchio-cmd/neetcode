# 238. Product of Array Except Self

🔗 [LeetCode](https://leetcode.com/problems/product-of-array-except-self/) | 📺 [NeetCode](https://neetcode.io/problems/products-of-array-discluding-self)

**Difficulty:** Medium
**Category:** Arrays & Hashing

## Problem

Given an integer array `nums`, return an array `output` such that `output[i]`
is equal to the product of all the elements of `nums` except `nums[i]`.

Constraints:
- The result is guaranteed to fit in a 32-bit integer.
- **Must run in O(n) time and use no division.**

## Approach

**Two-pass prefix/suffix products, in-place on the output array.**

The key observation: for any index `i`,
output[i] = (product of nums[0..i-1]) × (product of nums[i+1..n-1])
                   
So if we can quickly compute "left product" and "right product" for each
position, the answer for each `i` is just their multiplication.

To avoid allocating two extra arrays, we use the output array itself:

1. **First pass (left to right):** fill `result[i]` with the product of all
   elements to the left of `i`. `result[0] = 1` (nothing to the left of index 0).

2. **Second pass (right to left):** keep a running variable `rightProd` that
   accumulates the product of elements to the right. Multiply `result[i]` by
   `rightProd`, then update `rightProd` to include `nums[i]`.

After both passes, `result[i]` contains `leftProduct × rightProduct`, which is
exactly the answer.

## Complexity

- **Time:** O(n) — two linear passes
- **Space:** O(1) extra — the output array doesn't count toward extra space per problem constraints

## Why not use division?

The naive approach would be: compute the total product P, then `output[i] = P / nums[i]`.
This breaks in two ways:

- If **any** element is 0, division by zero crashes for that index.
- If **multiple** elements are 0, even the "valid" indices become wrong (the total product is 0 but the actual answer at non-zero indices should be 0 anyway, while the index of the single zero — if any — should be the product of the rest).

Avoiding division also makes the solution more general: it works for types
where division isn't even defined (e.g. matrices, polynomials).
