import pytest
from solution import Solution, SolutionArray


@pytest.mark.parametrize("sol_class", [Solution, SolutionArray])
class TestBothSolutions:

    def test_permutation_in_middle(self, sol_class):
        assert sol_class().checkInclusion("abc", "lecabee") is True

    def test_no_permutation(self, sol_class):
        assert sol_class().checkInclusion("abc", "lecaee") is False

    def test_permutation_at_start(self, sol_class):
        assert sol_class().checkInclusion("ab", "abcd") is True

    def test_permutation_at_end(self, sol_class):
        assert sol_class().checkInclusion("ab", "cdba") is True

    def test_s1_longer_than_s2(self, sol_class):
        assert sol_class().checkInclusion("abcd", "ab") is False

    def test_equal_length_match(self, sol_class):
        assert sol_class().checkInclusion("abc", "bca") is True

    def test_equal_length_no_match(self, sol_class):
        assert sol_class().checkInclusion("abc", "xyz") is False

    def test_repeated_characters(self, sol_class):
        assert sol_class().checkInclusion("aab", "aabc") is True
        assert sol_class().checkInclusion("aab", "xaby") is False

    def test_all_same(self, sol_class):
        assert sol_class().checkInclusion("aa", "aab") is True
        assert sol_class().checkInclusion("aaa", "aab") is False
