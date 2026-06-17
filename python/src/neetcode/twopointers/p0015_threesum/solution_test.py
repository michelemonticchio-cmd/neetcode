import pytest
from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    def normalize(self, triplets):
        return sorted([sorted(t) for t in triplets])

    def test_classic_example(self):
        result = self.sol.threeSum([-1, 0, 1, 2, -1, -4])
        assert self.normalize(result) == [[-1, -1, 2], [-1, 0, 1]]

    def test_no_triplet(self):
        assert self.sol.threeSum([1, 2, 3]) == []

    def test_all_zeros(self):
        assert self.sol.threeSum([0, 0, 0, 0]) == [[0, 0, 0]]

    def test_duplicates_in_input(self):
        result = self.sol.threeSum([-2, 0, 0, 0, 2])
        assert self.normalize(result) == [[-2, 0, 2]]

    def test_empty(self):
        assert self.sol.threeSum([]) == []

    def test_two_elements(self):
        assert self.sol.threeSum([1, -1]) == []

    def test_multiple_triplets(self):
        result = self.sol.threeSum([-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6])
        expected = [[-4, 0, 4], [-4, 1, 3], [-4, 2, 2],
                    [-2, -2, 4], [-2, 0, 2]]
        assert self.normalize(result) == self.normalize(expected)
