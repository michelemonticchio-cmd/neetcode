# 74. Search a 2D Matrix

🔗 [LeetCode](https://leetcode.com/problems/search-a-2d-matrix/) | 📺 [NeetCode](https://neetcode.io/problems/search-2d-matrix)

**Difficulty:** Medium
**Category:** Binary Search

## Problem

Given an `m x n` matrix where each row is sorted in non-decreasing order,
and the first element of each row is greater than the last element of the
previous row, return `true` if `target` exists in the matrix.

**Must run in O(log(m*n)) time.**

## Approach

**Binary search over a virtual 1D array.**

The two sorting properties together mean the matrix, read row by row, is
equivalent to one large sorted array of `rows * cols` elements:

    matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]]
    virtual array: [1,3,5,7,10,11,16,20,23,30,34,60]
    virtual index:  0 1 2 3 4  5  6  7  8  9  10 11

We run the exact same binary search as #704, except every time we need to
read a value at virtual index `mid`, we convert it to matrix coordinates:

    row = mid / cols
    col = mid % cols
    value = matrix[row][col]

No data is physically rearranged — the conversion is just an indexing trick.

## Complexity

- **Time:** O(log(m*n)) — a single binary search over `m*n` elements
- **Space:** O(1)

## Trace with matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3

    rows=3, cols=4, total=12, left=0, right=11

    mid=5  → row=1, col=1 → matrix[1][1]=11.  11>3  → right=4
    mid=2  → row=0, col=2 → matrix[0][2]=5.   5>3   → right=1
    mid=0  → row=0, col=0 → matrix[0][0]=1.   1<3   → left=1
    mid=1  → row=0, col=1 → matrix[0][1]=3.   3==3  → return true ✅

## Comparison with the row-scan approach

A simpler O(m + log n) approach: scan rows linearly to find the one whose
last element is `>= target`, then binary search within that row. This is
correct but slower for large `m`. The virtual-1D approach folds both steps
into a single O(log(m*n)) = O(log m + log n) search.

## Notes

This problem is a direct extension of Binary Search (#704): same algorithm,
with one added layer — translating a 1D index into 2D coordinates via
`/` and `%`. The same trick (treating a 2D structure as a flattened 1D
array) appears whenever a matrix has a total ordering across rows, not
just within each row.

Contrast with **Search a 2D Matrix II** (#240), where rows and columns are
sorted independently but there's no global ordering — that problem requires
a different approach (staircase search from a corner), since the
"flatten to 1D" trick doesn't apply.
