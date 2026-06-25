# 239. Sliding Window Maximum — Python

🔗 [LeetCode](https://leetcode.com/problems/sliding-window-maximum/) | 📺 [NeetCode](https://neetcode.io/problems/sliding-window-maximum)

**Difficulty:** Hard
**Category:** Sliding Window

## Problem

Given an array `nums` and a window size `k`, return the maximum value in
each window as it slides from left to right.

## Approach

**Monotonic decreasing deque of indices.**

Maintain a `deque` of indices such that their corresponding values are
always in decreasing order. The front is always the current maximum.

Three operations per step:
1. **Remove from right** — discard indices whose values are smaller than
   `nums[i]` (they can never be the maximum while `nums[i]` is in the window).
2. **Append** `i` to the right.
3. **Remove from left** — evict the front if it has slid out of the window
   (`q[0] < i - k + 1`).
4. **Record result** once the window is full (`i >= k - 1`).

## Why O(n)

Each index is appended once and removed at most once — total work across
all inner `while` iterations is O(n).

## Complexity

- **Time:** O(n)
- **Space:** O(k)

## Java vs Python deque API

| Java | Python |
|---|---|
| `new ArrayDeque<>()` | `deque()` from `collections` |
| `deque.peekLast()` | `q[-1]` |
| `deque.peekFirst()` | `q[0]` |
| `deque.removeLast()` | `q.pop()` |
| `deque.removeFirst()` | `q.popleft()` |
| `deque.addLast(i)` | `q.append(i)` |

## Naive alternative (O(n·k))

    result = [max(nums[i:i+k]) for i in range(len(nums)-k+1)]

Correct but too slow for large inputs.
