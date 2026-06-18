# 121. Best Time to Buy and Sell Stock — Python

🔗 [LeetCode](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | 📺 [NeetCode](https://neetcode.io/problems/buy-and-sell-crypto)

**Difficulty:** Easy
**Category:** Sliding Window

## Problem

Given an array `prices` where `prices[i]` is the price on day `i`, return
the maximum profit from one buy-then-sell transaction. Return 0 if no
profit is possible.

## Approach

**Single pass with a running minimum.**

For each price, either:
- Update `minPrice` if a cheaper buy day is found.
- Compute `price - minPrice` and update `maxProfit` if selling today
  beats the current best.

## Complexity

- **Time:** O(n)
- **Space:** O(1)

## Java vs Python

    // Java
    int minPrice = Integer.MAX_VALUE;

    # Python
    minPrice = float('inf')   # infinity — any real price will be smaller

Key differences:
- `float('inf')` instead of `Integer.MAX_VALUE`
- `for price in prices` instead of index-based for loop
- `max(a, b)` built-in instead of `Math.max(a, b)`
