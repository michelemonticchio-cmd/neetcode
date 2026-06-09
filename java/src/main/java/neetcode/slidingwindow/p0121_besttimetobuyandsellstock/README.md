# 121. Best Time to Buy and Sell Stock

🔗 [LeetCode](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | 📺 [NeetCode](https://neetcode.io/problems/buy-and-sell-crypto)

**Difficulty:** Easy
**Category:** Sliding Window

## Problem

Given an array `prices` where `prices[i]` is the price of an asset on day `i`,
choose one day to buy and a **strictly later** day to sell. Return the
maximum profit, or `0` if no profitable transaction is possible.

## Approach

**Single pass with a running minimum.**

For each day `i`, the best profit ending on day `i` is:

    profit_i = prices[i] - min(prices[0..i-1])

That is, to maximize profit selling on day `i`, you should have bought
at the lowest price among all earlier days. Tracking that running minimum
in a variable is enough — no need to re-scan history at every step.

Algorithm:
1. Initialize `minPrice` to a very large value and `maxProfit = 0`.
2. For each price:
   - Compute the hypothetical profit selling today (`price - minPrice`).
   - Update `maxProfit` if the profit is better.
   - Update `minPrice` to the current price if it's a new minimum.

The order — *first compute profit, then update minPrice* — is crucial:
it guarantees the "buy day < sell day" constraint, since `minPrice` at
iteration `i` only reflects days strictly before `i`.

## Why the order matters

Swapping the two updates would let us buy and sell on the same day:

    minPrice = min(minPrice, price)   // ❌ now minPrice == price
    profit = price - minPrice          // → 0 every time we hit a new low

Keeping `minPrice` "one step behind" is what enforces the temporal
constraint of the problem.

## Complexity

- **Time:** O(n) — single linear pass
- **Space:** O(1) — two scalar variables

This is asymptotically optimal: any solution must inspect every price
at least once.

## Connection to sliding window

This is the simplest "sliding window" problem: the implicit window has
its left boundary at the day of the running minimum and its right
boundary at the current iteration `i`. The window grows to the right
by one cell per iteration and "jumps" its left boundary whenever a new
minimum is found.

Subsequent sliding-window problems formalize this with two explicit
indices `left` and `right`, but the underlying idea is the same:
**maintain a summary of "the relevant past" and update it incrementally
as the window moves**.

## Pitfalls

- **Brute force is O(n²).** A natural first instinct is "try every (buy, sell)
  pair". This works but is too slow for large inputs. The single-pass
  insight collapses the inner loop into a constant-time update.
- **Don't return negative profit.** When all prices are monotonically
  decreasing (e.g., `[10, 7, 5, 2]`), no profitable trade exists and the
  answer is `0`, not `-8`. Initializing `maxProfit = 0` handles this
  automatically.
- **Update order.** As described above, compute profit *before* updating
  `minPrice`, not after.

## Notes

This problem teaches the pattern that recurs across the entire category:

> *"For each element `i`, keep a constant-size summary of the best thing
> seen up to `i-1`, and use it to make a local decision at `i`."*

The "best thing" varies — here it's the minimum, elsewhere it could be a
maximum, a sum, or a more complex state — but the **single-pass with
running state** template is one of the most reusable ideas in array
processing.

Related problems extending this idea:

- **Maximum Subarray** (#53, Kadane's algorithm) — track the best subarray
  ending at each position.
- **Best Time to Buy and Sell Stock II** (#122) — multiple transactions
  allowed.
- **Best Time to Buy and Sell Stock with Cooldown** (#309) — adds a
  one-day rest after each sell.
- **House Robber** (#198) — same template with a "skip one" constraint.
