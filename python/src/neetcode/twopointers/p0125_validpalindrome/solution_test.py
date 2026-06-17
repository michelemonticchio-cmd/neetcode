import pytest
from solution import Solution, SolutionTwoPointers


@pytest.mark.parametrize("sol_class", [Solution, SolutionTwoPointers])
class TestBothSolutions:

    def test_classic_palindrome(self, sol_class):
        assert sol_class().isPalindrome("A man, a plan, a canal: Panama") is True

    def test_not_palindrome(self, sol_class):
        assert sol_class().isPalindrome("race a car") is False

    def test_empty_string(self, sol_class):
        assert sol_class().isPalindrome("") is True

    def test_only_symbols(self, sol_class):
        assert sol_class().isPalindrome(".,!?") is True

    def test_single_char(self, sol_class):
        assert sol_class().isPalindrome("a") is True

    def test_case_insensitive(self, sol_class):
        assert sol_class().isPalindrome("AbBa") is True

    def test_alphanumeric_mixed(self, sol_class):
        assert sol_class().isPalindrome("0P") is False
