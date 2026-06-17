import pytest
from solution import SolutionCounter, SolutionBucketSort


@pytest.mark.parametrize("sol_class", [SolutionCounter, SolutionBucketSort])
class TestBothSolutions:

    def test_basic_k2(self, sol_class):
        result = sol_class().topKFrequent([1, 1, 1, 2, 2, 3], 2)
        assert sorted(result) == [1, 2]

    def test_basic_k1(self, sol_class):
        result = sol_class().topKFrequent([1], 1)
        assert result == [1]

    def test_all_same(self, sol_class):
        result = sol_class().topKFrequent([4, 4, 4, 4], 1)
        assert result == [4]

    def test_k_equals_length(self, sol_class):
        result = sol_class().topKFrequent([1, 2, 3], 3)
        assert sorted(result) == [1, 2, 3]

    def test_negative_numbers(self, sol_class):
        result = sol_class().topKFrequent([-1, -1, 2, 2, 3], 2)
        assert sorted(result) == [-1, 2]

    def test_tie_in_frequency(self, sol_class):
        # Both 1 and 2 appear twice, k=1 → either is valid
        result = sol_class().topKFrequent([1, 1, 2, 2], 1)
        assert result[0] in [1, 2]
        assert len(result) == 1
