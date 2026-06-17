# 238. Product of Array Except Self — Python

🔗 [LeetCode](https://leetcode.com/problems/product-of-array-except-self/) | 📺 [NeetCode](https://neetcode.io/problems/products-of-array-discluding-self)

**Difficulty:** Medium
**Category:** Arrays & Hashing

## Problem

Given an integer array `nums`, return an array `output` where `output[i]`
is the product of all elements of `nums` except `nums[i]`.
Must run in O(n) without using division.

## Key insight

For every index `i`:

    output[i] = (product of all elements LEFT of i)
              × (product of all elements RIGHT of i)

Both halves can be computed incrementally in a single pass each.

## Approach: two-pass prefix/suffix

### Pass 1 — prefix (left to right)

    prefix = 1
    for i in range(n):
        output[i] = prefix      # product of everything before i
        prefix *= nums[i]

After pass 1, `output[i]` holds the product of `nums[0..i-1]`.

### Pass 2 — suffix (right to left)

    suffix = 1
    for i in range(n-1, -1, -1):
        output[i] *= suffix     # multiply by product of everything after i
        suffix *= nums[i]

## Trace with nums = [1, 2, 3, 4]

    After pass 1: output = [1, 1, 2, 6]
                  (prefix products: 1, 1×1, 1×2, 1×2×3)

    Pass 2 (suffix=1):
      i=3: output[3] = 6×1=6,   suffix=4
      i=2: output[2] = 2×4=8,   suffix=12
      i=1: output[1] = 1×12=12, suffix=24
      i=0: output[0] = 1×24=24, suffix=24

    output = [24, 12, 8, 6] ✅

## Complexity

- **Time:** O(n) — two linear passes
- **Space:** O(1) extra — only `prefix` and `suffix` scalars;
  the output array is the required return value and doesn't count

## Python tools used

- `[1] * n` — creates a list of n ones, like `new int[n]` in Java
  (Java initializes to 0, Python `[1]*n` initializes to 1)
- `range(n-1, -1, -1)` — descending range from n-1 to 0 inclusive

## Java vs Python

    // Java
    int[] output = new int[n];
    Arrays.fill(output, 1);
    int prefix = 1;
    for (int i = 0; i < n; i++) {
        output[i] = prefix;
        prefix *= nums[i];
    }

    # Python
    output = [1] * n
    prefix = 1
    for i in range(n):
        output[i] = prefix
        prefix *= nums[i]
