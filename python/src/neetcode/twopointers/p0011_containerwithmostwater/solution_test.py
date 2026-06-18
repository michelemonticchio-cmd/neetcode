import pytest
from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    def test_classic_example(self):
        assert self.sol.maxArea([1, 8, 6, 2, 5, 4, 8, 3, 7]) == 49

    def test_two_elements(self):
        assert self.sol.maxArea([1, 1]) == 1

    def test_increasing(self):
        assert self.sol.maxArea([1, 2, 3, 4, 5]) == 6

    def test_decreasing(self):
        assert self.sol.maxArea([5, 4, 3, 2, 1]) == 6

    def test_all_same(self):
        assert self.sol.maxArea([3, 3, 3, 3]) == 9

    def test_tall_in_middle(self):
        assert self.sol.maxArea([1, 10, 1]) == 2

    def test_zeros_at_ends(self):
        assert self.sol.maxArea([0, 5, 5, 0]) == 5
