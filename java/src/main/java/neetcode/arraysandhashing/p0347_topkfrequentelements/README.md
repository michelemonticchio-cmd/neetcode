# 347. Top K Frequent Elements

🔗 [LeetCode](https://leetcode.com/problems/top-k-frequent-elements/) | 📺 [NeetCode](https://neetcode.io/problems/top-k-elements-in-list)

**Difficulty:** Medium
**Category:** Arrays & Hashing

## Problem

Given an integer array `nums` and an integer `k`, return the `k` most frequent
elements. The answer may be returned in any order.

## Approach

**Bucket Sort by frequency.** The key observation is that the frequency of any
element in an array of length `n` is bounded by `n`, so we can use an array
indexed by frequency to bucket the elements.

1. Count occurrences with a HashMap (`HashMap<Integer, Integer>`).
2. Create a `buckets` array of size `n + 1`, where `buckets[f]` is the list
   of numbers appearing exactly `f` times.
3. Populate the buckets by walking the count map.
4. Scan `buckets` from the highest index (highest frequency) down to 0,
   collecting numbers until we have `k`.

Since frequencies are bounded, indexing by frequency turns the "find top k"
problem from a sort (O(n log n)) or heap (O(n log k)) into a linear scan.

## Complexity

- **Time:** O(n) — every phase (count, fill buckets, scan) is linear in `n`
- **Space:** O(n) — for the count map and the buckets array

## Alternative approaches

- **Sort by frequency:** O(n log n) — sort all distinct elements by count,
  take the first k. Simple but asymptotically slower.
- **Min-heap of size k:** O(n log k) — keep a `PriorityQueue` of size k and
  evict the smallest as you go. Classic interview solution, but still
  slower than bucket sort.

## Notes

This problem is a clean illustration of a recurring trick:
**when a value has a small bounded range, use that range as an index** to
replace `sort/heap` operations with array lookups.
You'll see the same pattern in Counting Sort, Radix Sort, and several
sliding-window problems with fixed alphabets (e.g. Valid Anagram).

## Pitfall

Watch out for an off-by-one error: the buckets array has size `n + 1` because
the maximum possible frequency is `n` (when all elements are equal). The
initialization loop must run `i <= n`, not `i < n`. Forgetting this leaves
`buckets[n]` as `null` and crashes with `NullPointerException` on inputs like
`[1,1,1]` with `k = 1`.
