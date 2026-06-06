# 36. Valid Sudoku

🔗 [LeetCode](https://leetcode.com/problems/valid-sudoku/) | 📺 [NeetCode](https://neetcode.io/problems/valid-sudoku)

**Difficulty:** Medium
**Category:** Arrays & Hashing

## Problem

Given a 9×9 Sudoku board (a `char[][]`), determine if it is **valid**. A board
is valid if:

1. Each row contains the digits `1-9` without duplicates.
2. Each column contains the digits `1-9` without duplicates.
3. Each of the nine 3×3 sub-boxes contains the digits `1-9` without duplicates.

The board may be partially filled — empty cells are marked with `.`. The board
does not need to be solvable to be valid; we only check the existing entries.

## Approach

**27 HashSets, one pass over the board.** This problem extends the
"hashset to detect duplicates" pattern from Contains Duplicate (#217) to
three dimensions simultaneously.

We maintain:
- 9 sets for rows
- 9 sets for columns
- 9 sets for the 3×3 sub-boxes

Then we iterate over the 81 cells once. For each non-empty cell, we check
whether the digit is already present in the corresponding row set, column
set, or box set. If yes, the board is invalid. Otherwise, we add the digit
to all three sets and continue.

We exploit a useful property: `Set.add()` returns `false` when the element
was already present. This lets us combine "check and insert" into a single
expression with short-circuit `||`.

### Mapping `(row, col)` to box index

Each cell belongs to one of nine 3×3 boxes, numbered 0..8 like this:

       col: 0-2  3-5  6-8
    row:
    0-2     0    1    2
    3-5     3    4    5
    6-8     6    7    8

The formula:

    boxIndex = (row / 3) * 3 + (col / 3)

In Java, `/` on `int` is integer division (truncates), so `r/3` groups three
rows together and `c/3` groups three columns. Multiplying the row group by 3
and adding the column group flattens the 2D box coordinates into a single
0..8 index — the same trick used to index a 2D matrix stored in a 1D array.

## Complexity

- **Time:** O(1) — the board size is fixed at 81 cells
- **Space:** O(1) — 27 sets with at most 9 elements each

For a generalized N×N Sudoku, both would be O(N²).

## Notes

This problem is a clean illustration of **stacking the same pattern in
multiple dimensions**. The single-set "detect duplicates" trick scales
naturally to checking 27 independent constraints in one pass — no need
for three separate iterations over the board.

The `(r/3)*3 + (c/3)` formula is worth memorizing: variants of it appear
whenever you need to flatten 2D coordinates into a 1D index, or partition
a grid into block regions.

## Alternative

A more verbose version uses explicit `contains()` checks before `add()`:

    if (rows[r].contains(ch) || cols[c].contains(ch) || boxes[boxIndex].contains(ch)) {
        return false;
    }
    rows[r].add(ch);
    cols[c].add(ch);
    boxes[boxIndex].add(ch);

Asymptotically identical; the compact `add()` version above is preferred
once one is comfortable with the short-circuit-`||` idiom.
