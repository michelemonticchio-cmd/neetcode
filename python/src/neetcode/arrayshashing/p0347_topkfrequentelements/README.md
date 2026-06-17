# 347. Top K Frequent Elements — Python

🔗 [LeetCode](https://leetcode.com/problems/top-k-frequent-elements/) | 📺 [NeetCode](https://neetcode.io/problems/top-k-elements-in-list)

**Difficulty:** Medium
**Category:** Arrays & Hashing

## Problem

Given an integer array `nums` and an integer `k`, return the `k` most
frequent elements in any order.

## Approach A — Counter.most_common(k)

    count = Counter(nums)
    return [n for n, freq in count.most_common(k)]

`Counter.most_common(k)` returns the k most frequent elements as
`(element, frequency)` pairs, already sorted by frequency descending.
Internally uses a heap → O(n log k).

The list comprehension `[n for n, freq in ...]` unpacks each pair and
keeps only the element (discarding the frequency).

## Approach B — Bucket Sort (O(n))

The maximum possible frequency is `n` (when all elements are equal).
Create `n+1` buckets where `bucket[freq]` holds all elements with that
frequency. Scan from right (highest frequency) and collect k elements.

    bucket = [[] for _ in range(len(nums) + 1)]
    for n, freq in count.items():
        bucket[freq].append(n)

    result = []
    for i in range(len(bucket) - 1, 0, -1):
        for n in bucket[i]:
            result.append(n)
            if len(result) == k:
                return result

This avoids sorting entirely → true O(n).

## Complexity

| Approach | Time | Space |
|---|---|---|
| Counter.most_common | O(n log k) | O(n) |
| Bucket Sort | O(n) | O(n) |

## Java vs Python

    // Java (bucket sort)
    int[] count = new int[n+1];
    List<Integer>[] bucket = new List[n+1];
    // ... fill buckets, scan right to left

    # Python
    bucket = [[] for _ in range(len(nums) + 1)]
    for n, freq in count.items():
        bucket[freq].append(n)

Key Python tools:
- `Counter(nums)` — instant frequency map
- `[[] for _ in range(n)]` — list of empty lists (list comprehension)
- `range(len(bucket)-1, 0, -1)` — range decrescente
