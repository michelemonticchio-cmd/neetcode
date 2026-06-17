import pytest
from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    VALID_BOARD = [
        ["5","3",".",".","7",".",".",".","."],
        ["6",".",".","1","9","5",".",".","."],
        [".","9","8",".",".",".",".","6","."],
        ["8",".",".",".","6",".",".",".","3"],
        ["4",".",".","8",".","3",".",".","1"],
        ["7",".",".",".","2",".",".",".","6"],
        [".","6",".",".",".",".","2","8","."],
        [".",".",".","4","1","9",".",".","5"],
        [".",".",".",".","8",".",".","7","9"],
    ]

    INVALID_ROW = [
        ["5","3",".",".","7",".",".",".","."],
        ["6",".",".","1","9","5",".",".","."],
        [".","9","8",".",".",".",".","6","."],
        ["8",".",".",".","6",".",".",".","3"],
        ["4",".",".","8",".","3",".",".","1"],
        ["7",".",".",".","2",".",".",".","6"],
        [".","6",".",".",".",".","2","8","."],
        [".",".",".","4","1","9",".",".","5"],
        [".",".",".",".","8",".",".","7","7"],  # ← duplicate 7 in last row
    ]

    INVALID_BOX = [
        ["8","3",".",".","7",".",".",".","."],
        ["6",".",".","1","9","5",".",".","."],
        [".","9","8",".",".",".",".","6","."],  # ← 8 appears twice in box 0
        ["8",".",".",".","6",".",".",".","3"],
        ["4",".",".","8",".","3",".",".","1"],
        ["7",".",".",".","2",".",".",".","6"],
        [".","6",".",".",".",".","2","8","."],
        [".",".",".","4","1","9",".",".","5"],
        [".",".",".",".","8",".",".","7","9"],
    ]

    def test_valid_board(self):
        assert self.sol.isValidSudoku(self.VALID_BOARD) is True

    def test_invalid_row(self):
        assert self.sol.isValidSudoku(self.INVALID_ROW) is False

    def test_invalid_box(self):
        assert self.sol.isValidSudoku(self.INVALID_BOX) is False

    def test_empty_board(self):
        board = [["." for _ in range(9)] for _ in range(9)]
        assert self.sol.isValidSudoku(board) is True

    def test_single_digit(self):
        board = [["." for _ in range(9)] for _ in range(9)]
        board[0][0] = "5"
        assert self.sol.isValidSudoku(board) is True

    def test_duplicate_in_column(self):
        board = [["." for _ in range(9)] for _ in range(9)]
        board[0][0] = "5"
        board[1][0] = "5"   # duplicate 5 in column 0
        assert self.sol.isValidSudoku(board) is False
