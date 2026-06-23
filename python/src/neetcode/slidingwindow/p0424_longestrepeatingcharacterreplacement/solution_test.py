from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    def test_neetcode_example_one(self):
        assert self.sol.characterReplacement("XYYX", 2) == 4

    def test_neetcode_example_two(self):
        assert self.sol.characterReplacement("AAABABB", 1) == 5

    def test_leetcode_classic(self):
        assert self.sol.characterReplacement("ABAB", 2) == 4

    def test_zero_replacements(self):
        assert self.sol.characterReplacement("ABBBC", 0) == 3

    def test_single_character(self):
        assert self.sol.characterReplacement("A", 5) == 1

    def test_all_same(self):
        assert self.sol.characterReplacement("AAAAAAA", 2) == 7

    def test_k_larger_than_string(self):
        assert self.sol.characterReplacement("ABCDE", 10) == 5

    def test_alternating(self):
        assert self.sol.characterReplacement("ABABBA", 2) == 5
