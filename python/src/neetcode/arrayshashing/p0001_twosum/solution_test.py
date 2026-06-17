import pytest
from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    def test_basic(self):
        assert self.sol.twoSum([2, 7, 11, 15], 9) == [0, 1]

    def test_pair_in_middle(self):
        assert self.sol.twoSum([3, 2, 4], 6) == [1, 2]

    def test_same_element_twice(self):
        assert self.sol.twoSum([3, 3], 6) == [0, 1]

    def test_negative_numbers(self):
        assert self.sol.twoSum([-3, 4, 3, 90], 0) == [0, 2]

    def test_target_zero(self):
        assert self.sol.twoSum([-1, 1, 2, 3], 0) == [0, 1]

    def test_pair_at_end(self):
        assert self.sol.twoSum([1, 2, 3, 4], 7) == [2, 3]
