# 36. Valid Sudoku — Python

🔗 [LeetCode](https://leetcode.com/problems/valid-sudoku/) | 📺 [NeetCode](https://neetcode.io/problems/valid-sudoku)

**Difficulty:** Medium
**Category:** Arrays & Hashing

## Problem

Determine if a 9x9 Sudoku board is valid. A board is valid if:
- Each row contains no duplicate digits.
- Each column contains no duplicate digits.
- Each of the nine 3x3 boxes contains no duplicate digits.

Empty cells are represented by `'.'` and are ignored.

## Approach

**Single set of tuples as a unified duplicate tracker.**

For each digit found at `(row, col)`, insert three tuples into a set:

    (digit, "row", row)               # seen in this row
    (digit, "col", col)               # seen in this column
    (digit, "box", (row//3)*3+col//3) # seen in this 3x3 box

If any tuple is already present, a duplicate exists → invalid.

### Box index formula

The grid is divided into nine 3x3 boxes numbered 0-8:

    box 0 | box 1 | box 2
    ──────┼───────┼──────
    box 3 | box 4 | box 5
    ──────┼───────┼──────
    box 6 | box 7 | box 8

    box_index = (row // 3) * 3 + (col // 3)

## Complexity

- **Time:** O(1) — fixed 9×9 grid
- **Space:** O(1) — at most 3×81 tuples in the set

## Why tuples work as set keys

Python tuples are immutable and hashable, so they can be elements of a
`set` or keys of a `dict`. This lets a single set replace 27 separate
HashSets (9 rows + 9 cols + 9 boxes) used in the Java solution.

## Java vs Python

    // Java: 27 separate HashSet objects
    Set<String>[] rows = new HashSet[9];
    Set<String>[] cols = new HashSet[9];
    Set<String>[] boxes = new HashSet[9];

    # Python: one set of tuples
    seen = set()
    seen.add((num, "row", row))
    seen.add((num, "col", col))
    seen.add((num, "box", (row//3)*3 + col//3))
