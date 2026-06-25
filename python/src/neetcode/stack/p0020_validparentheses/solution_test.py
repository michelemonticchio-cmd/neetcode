from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    def test_simple_valid(self):
        assert self.sol.isValid("()") is True
        assert self.sol.isValid("[]") is True
        assert self.sol.isValid("{}") is True

    def test_nested_valid(self):
        assert self.sol.isValid("([{}])") is True

    def test_sequential_valid(self):
        assert self.sol.isValid("()[]{}") is True

    def test_wrong_order(self):
        assert self.sol.isValid("([)]") is False

    def test_unclosed_opener(self):
        assert self.sol.isValid("(((") is False
        assert self.sol.isValid("([]") is False

    def test_closer_without_opener(self):
        assert self.sol.isValid(")") is False

    def test_empty_string(self):
        assert self.sol.isValid("") is True

    def test_mismatched_types(self):
        assert self.sol.isValid("(]") is False
        assert self.sol.isValid("{)") is False
