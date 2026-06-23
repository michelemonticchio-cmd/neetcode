from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    def test_neetcode_example(self):
        assert self.sol.minWindow("OUZODYXAZV", "XYZ") == "YXAZ"

    def test_leetcode_classic(self):
        assert self.sol.minWindow("ADOBECODEBANC", "ABC") == "BANC"

    def test_single_char_match(self):
        assert self.sol.minWindow("a", "a") == "a"

    def test_no_valid_window(self):
        assert self.sol.minWindow("a", "aa") == ""

    def test_t_longer_than_s(self):
        assert self.sol.minWindow("ab", "abc") == ""

    def test_empty_t(self):
        assert self.sol.minWindow("abc", "") == ""

    def test_empty_s(self):
        assert self.sol.minWindow("", "a") == ""

    def test_duplicates_in_t(self):
        assert self.sol.minWindow("aa", "aa") == "aa"
        assert self.sol.minWindow("a", "aa") == ""

    def test_whole_string_is_minimum(self):
        assert self.sol.minWindow("AECBAD", "ABCDE") == "AECBAD"
