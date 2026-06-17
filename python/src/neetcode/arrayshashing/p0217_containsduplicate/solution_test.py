import pytest
from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    def test_has_duplicate(self):
        assert self.sol.hasDuplicate([1, 2, 3, 1]) is True

    def test_no_duplicate(self):
        assert self.sol.hasDuplicate([1, 2, 3, 4]) is False

    def test_all_same(self):
        assert self.sol.hasDuplicate([1, 1, 1, 1]) is True

    def test_empty(self):
        assert self.sol.hasDuplicate([]) is False

    def test_single_element(self):
        assert self.sol.hasDuplicate([42]) is False

    def test_two_elements_duplicate(self):
        assert self.sol.hasDuplicate([1, 1]) is True

    def test_two_elements_no_duplicate(self):
        assert self.sol.hasDuplicate([1, 2]) is False

    def test_large_range_no_duplicate(self):
        assert self.sol.hasDuplicate(list(range(1000))) is False

    def test_large_range_with_duplicate(self):
        nums = list(range(1000))
        nums.append(500)
        assert self.sol.hasDuplicate(nums) is True
