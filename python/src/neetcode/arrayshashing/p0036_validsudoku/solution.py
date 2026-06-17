from typing import List


class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        """
        Returns True if the 9x9 Sudoku board is valid.
        A board is valid if no row, column, or 3x3 box contains a duplicate digit.
        Empty cells ('.') are ignored.

        Approach: single set of tuples as a unified seen-tracker.
        For each digit found, three tuples are inserted:
          (digit, "row", row_index)
          (digit, "col", col_index)
          (digit, "box", box_index)   box_index = (row//3)*3 + col//3
        If any tuple is already in the set, a duplicate exists.

        Time:  O(1) — fixed 9x9 grid, constant number of cells
        Space: O(1) — at most 27*81 tuples in the set (constant)
        """
        seen = set()

        for row in range(9):
            for col in range(9):
                num = board[row][col]
                if num == '.':
                    continue

                row_key = (num, "row", row)
                col_key = (num, "col", col)
                box_key = (num, "box", (row // 3) * 3 + col // 3)

                if row_key in seen or col_key in seen or box_key in seen:
                    return False

                seen.add(row_key)
                seen.add(col_key)
                seen.add(box_key)

        return True
