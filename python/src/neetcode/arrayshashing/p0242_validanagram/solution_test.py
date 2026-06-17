import pytest
from solution import SolutionCounter, SolutionDict


@pytest.mark.parametrize("sol_class", [SolutionCounter, SolutionDict])
class TestBothSolutions:
    """Runs the same tests against both implementations."""

    def test_classic_anagram(self, sol_class):
        assert sol_class().isAnagram("racecar", "carrace") is True

    def test_not_anagram(self, sol_class):
        assert sol_class().isAnagram("rat", "car") is False

    def test_different_lengths(self, sol_class):
        assert sol_class().isAnagram("ab", "abc") is False

    def test_same_string(self, sol_class):
        assert sol_class().isAnagram("hello", "hello") is True

    def test_single_char_match(self, sol_class):
        assert sol_class().isAnagram("a", "a") is True

    def test_single_char_no_match(self, sol_class):
        assert sol_class().isAnagram("a", "b") is False

    def test_duplicate_chars(self, sol_class):
        # "aab" and "baa" are anagrams
        assert sol_class().isAnagram("aab", "baa") is True

    def test_same_chars_different_freq(self, sol_class):
        # "aab" and "abb" share chars but different frequencies
        assert sol_class().isAnagram("aab", "abb") is False

    def test_empty_strings(self, sol_class):
        assert sol_class().isAnagram("", "") is True
