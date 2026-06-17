import pytest
from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    def normalize(self, groups):
        """Sort each group and the list of groups for order-independent comparison."""
        return sorted([sorted(g) for g in groups])

    def test_classic_example(self):
        result = self.sol.groupAnagrams(["eat", "tea", "tan", "ate", "nat", "bat"])
        expected = [["ate", "eat", "tea"], ["nat", "tan"], ["bat"]]
        assert self.normalize(result) == self.normalize(expected)

    def test_single_word(self):
        assert self.normalize(self.sol.groupAnagrams(["a"])) == [["a"]]

    def test_empty_string(self):
        assert self.normalize(self.sol.groupAnagrams([""])) == [[""]]

    def test_all_anagrams(self):
        result = self.sol.groupAnagrams(["abc", "bca", "cab"])
        assert self.normalize(result) == [["abc", "bca", "cab"]]

    def test_no_anagrams(self):
        result = self.sol.groupAnagrams(["abc", "def", "ghi"])
        expected = [["abc"], ["def"], ["ghi"]]
        assert self.normalize(result) == self.normalize(expected)

    def test_multiple_empty_strings(self):
        result = self.sol.groupAnagrams(["", "", ""])
        assert self.normalize(result) == [["", "", ""]]

    def test_single_chars(self):
        result = self.sol.groupAnagrams(["a", "b", "a"])
        expected = [["a", "a"], ["b"]]
        assert self.normalize(result) == self.normalize(expected)
