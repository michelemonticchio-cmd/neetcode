package neetcode.arraysandhashing.p0036_validsudoku;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    /**
     * Validates a partially filled 9x9 Sudoku board.
     * Checks that each row, column, and 3x3 sub-box contains no duplicate digits.
     * Empty cells (marked '.') are ignored.
     *
     * Time:  O(1) — board size is fixed (81 cells)
     * Space: O(1) — at most 9 elements per set, 27 sets total
     */
    @SuppressWarnings("unchecked")
    public boolean isValidSudoku(char[][] board) {
        // 9 sets for rows, 9 for columns, 9 for the 3x3 sub-boxes
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] boxes = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') continue;

                // Map (row, col) → box index in 0..8
                int boxIndex = (r / 3) * 3 + (c / 3);

                // If the digit already appears in row, column, or box → invalid
                if (rows[r].contains(ch) ||
                    cols[c].contains(ch) ||
                    boxes[boxIndex].contains(ch)) {
                    return false;
                }

                rows[r].add(ch);
                cols[c].add(ch);
                boxes[boxIndex].add(ch);
            }
        }

        return true;
    }
}
