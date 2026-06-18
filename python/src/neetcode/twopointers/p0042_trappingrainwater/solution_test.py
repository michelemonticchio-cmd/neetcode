import pytest
from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    def test_classic_example(self):
        assert self.sol.trap([0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]) == 6

    def test_neetcode_example(self):
        assert self.sol.trap([4, 2, 0, 3, 2, 5]) == 9

    def test_no_water_decreasing(self):
        assert self.sol.trap([5, 4, 3, 2, 1]) == 0

    def test_no_water_increasing(self):
        assert self.sol.trap([1, 2, 3, 4, 5]) == 0

    def test_single_valley(self):
        assert self.sol.trap([5, 0, 5]) == 5

    def test_flat(self):
        assert self.sol.trap([3, 3, 3, 3]) == 0

    def test_single_element(self):
        assert self.sol.trap([5]) == 0

    def test_two_elements(self):
        assert self.sol.trap([5, 3]) == 0

    def test_water_over_intermediate_wall(self):
        assert self.sol.trap([3, 0, 1, 0, 3]) == 8
