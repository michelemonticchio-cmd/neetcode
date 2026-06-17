import pytest
from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    def test_classic_example(self):
        assert self.sol.longestConsecutive([100, 4, 200, 1, 3, 2]) == 4

    def test_already_sorted(self):
        assert self.sol.longestConsecutive([1, 2, 3, 4, 5]) == 5

    def test_single_element(self):
        assert self.sol.longestConsecutive([5]) == 1

    def test_empty(self):
        assert self.sol.longestConsecutive([]) == 0

    def test_all_duplicates(self):
        assert self.sol.longestConsecutive([1, 1, 1, 1]) == 1

    def test_two_separate_sequences(self):
        # [1,2,3] and [10,11,12] → longest is 3
        assert self.sol.longestConsecutive([1, 2, 3, 10, 11, 12]) == 3

    def test_negative_numbers(self):
        assert self.sol.longestConsecutive([-3, -2, -1, 0, 1]) == 5

    def test_duplicates_in_sequence(self):
        # Duplicates don't extend the sequence
        assert self.sol.longestConsecutive([1, 2, 2, 3]) == 3

    def test_large_gap(self):
        assert self.sol.longestConsecutive([1, 100]) == 1
