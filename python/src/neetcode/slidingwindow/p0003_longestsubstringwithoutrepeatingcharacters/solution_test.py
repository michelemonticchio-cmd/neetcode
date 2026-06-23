import pytest
from solution import Solution, SolutionSet


@pytest.mark.parametrize("sol_class", [Solution, SolutionSet])
class TestBothSolutions:

    def test_classic_example(self, sol_class):
        assert sol_class().lengthOfLongestSubstring("abcabcbb") == 3

    def test_all_same(self, sol_class):
        assert sol_class().lengthOfLongestSubstring("bbbbb") == 1

    def test_neetcode_example(self, sol_class):
        assert sol_class().lengthOfLongestSubstring("zxyzxyz") == 3

    def test_empty_string(self, sol_class):
        assert sol_class().lengthOfLongestSubstring("") == 0

    def test_single_char(self, sol_class):
        assert sol_class().lengthOfLongestSubstring("a") == 1

    def test_all_unique(self, sol_class):
        assert sol_class().lengthOfLongestSubstring("abcdef") == 6

    def test_pwwkew(self, sol_class):
        assert sol_class().lengthOfLongestSubstring("pwwkew") == 3

    def test_dvdf(self, sol_class):
        # Tests correct left pointer handling
        assert sol_class().lengthOfLongestSubstring("dvdf") == 3

    def test_abba(self, sol_class):
        assert sol_class().lengthOfLongestSubstring("abba") == 2
