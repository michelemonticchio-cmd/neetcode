import pytest
from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    def test_basic(self):
        assert self.sol.twoSum([2, 7, 11, 15], 9) == [1, 2]

    def test_pair_in_middle(self):
        assert self.sol.twoSum([2, 7, 11, 15], 18) == [2, 3]

    def test_first_and_last(self):
        assert self.sol.twoSum([1, 2, 3, 4], 5) == [1, 4]

    def test_with_negatives(self):
        assert self.sol.twoSum([-5, 1, 3, 10], 5) == [1, 4]

    def test_two_elements(self):
        assert self.sol.twoSum([1, 2], 3) == [1, 2]

    def test_duplicates(self):
        assert self.sol.twoSum([3, 3], 6) == [1, 2]

    def test_all_negative(self):
        assert self.sol.twoSum([-5, -3, -2, -1], -4) == [2, 4]
