# 981. Time Based Key-Value Store

🔗 [LeetCode](https://leetcode.com/problems/time-based-key-value-store/) | 📺 [NeetCode](https://neetcode.io/problems/time-based-key-value-store)

**Difficulty:** Medium
**Category:** Binary Search

## Problem

Design a data structure `TimeMap` that:
- `set(key, value, timestamp)` — stores `value` for `key` at `timestamp`.
- `get(key, timestamp)` — returns the value associated with the **largest
  stored timestamp ≤ `timestamp`** for that key, or `""` if none exists.

All `set` calls arrive with **strictly increasing timestamps** for each key.

## Approach

**Two parallel maps + binary search for "rightmost timestamp ≤ target".**

For each key maintain two `ArrayList`s in lock-step:
- `timestamps.get(key)` — list of timestamps in insertion (= ascending) order.
- `values.get(key)` — corresponding values.

Because timestamps are inserted in ascending order, the list is always sorted,
enabling binary search on `get`.

### Binary search variant: rightmost ≤ target

Unlike standard binary search (which returns immediately on a match), here we
want the **largest** timestamp that does not exceed the query:

    while left <= right:
        mid = (left + right) / 2
        if times[mid] <= timestamp:
            result = mid          // valid candidate
            left = mid + 1        // but maybe there's a later valid one
        else:
            right = mid - 1

When the loop ends, `result` holds the index of the rightmost valid timestamp,
or `-1` if no timestamp satisfied the condition.

## Complexity

- **`set`:** O(1) amortized (ArrayList append)
- **`get`:** O(log n) where n = number of set calls for that key
- **Space:** O(n) total across all keys

## Trace

    set("foo", "bar",  1) → times=["foo":[1]],   vals=["foo":["bar"]]
    set("foo", "bar2", 4) → times=["foo":[1,4]], vals=["foo":["bar","bar2"]]

    get("foo", 4):  binary search [1,4] for <= 4
      mid=0: 1<=4 → result=0, left=1
      mid=1: 4<=4 → result=1, left=2 (exit)
      → values[1] = "bar2" ✅

    get("foo", 3):  binary search [1,4] for <= 3
      mid=0: 1<=3 → result=0, left=1
      mid=1: 4<=3? No → right=0 (exit)
      → values[0] = "bar" ✅

    get("foo", 0):  binary search [1,4] for <= 0
      mid=0: 1<=0? No → right=-1 (exit)
      result=-1 → return "" ✅

## Pitfalls

- **`left = mid + 1`, not `left = mid`.** When a match is found, we still
  advance `left` to search for a later valid timestamp. Writing `left = mid`
  causes an infinite loop when `left == mid`.
- **Parallel list consistency.** `timestamps` and `values` must always be
  kept in sync — every `add` to one must have a corresponding `add` to the
  other at the same index.
- **`putIfAbsent` on both maps.** Missing this on either map causes a
  `NullPointerException` on the first `set` for a new key.

## Alternative design

A single `Map<String, List<int[]>>` where each entry is `[timestamp, valueIndex]`
paired with a separate value store, or a `Map<String, TreeMap<Integer, String>>`
where `TreeMap.floorKey(timestamp)` does the "rightmost ≤" lookup in O(log n)
without manual binary search:

    TreeMap<Integer, String> map = store.get(key);
    Integer floor = map.floorKey(timestamp);
    return floor == null ? "" : map.get(floor);

The two-list approach is kept here for explicitness; the `TreeMap` variant
is more idiomatic Java when code brevity matters.

## Notes

This problem combines two ideas: a **data structure design** (how to
organize values-per-key-per-time) and a **binary search variant**
(rightmost element satisfying a condition). The binary search variant —
"find the largest value ≤ target, keep `result` as a candidate and keep
searching right" — is a recurring template distinct from:

- **Classic binary search** (#704): return immediately on exact match.
- **Find Minimum** (#153): search for a boundary, use `left < right`.
- **This problem**: track the best candidate seen so far, keep searching.
