import pytest
from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    def test_basic(self):
        assert self.sol.productExceptSelf([1, 2, 3, 4]) == [24, 12, 8, 6]

    def test_with_zero(self):
        # Only one zero: all positions get 0 except the zero position
        assert self.sol.productExceptSelf([1, 0, 3, 4]) == [0, 12, 0, 0]

    def test_two_zeros(self):
        # Two zeros: all positions get 0
        assert self.sol.productExceptSelf([0, 0, 3, 4]) == [0, 0, 0, 0]

    def test_with_negative(self):
        assert self.sol.productExceptSelf([-1, 2, 3, 4]) == [24, -12, -8, -6]

    def test_two_elements(self):
        assert self.sol.productExceptSelf([3, 4]) == [4, 3]

    def test_all_ones(self):
        assert self.sol.productExceptSelf([1, 1, 1, 1]) == [1, 1, 1, 1]

    def test_single_element(self):
        assert self.sol.productExceptSelf([5]) == [1]

    def test_large_values(self):
        assert self.sol.productExceptSelf([2, 3, 4, 5]) == [60, 40, 30, 24]
