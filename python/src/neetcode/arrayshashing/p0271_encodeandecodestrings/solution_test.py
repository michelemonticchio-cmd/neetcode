import pytest
from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    def roundtrip(self, strs):
        """Encode then decode should return the original list."""
        return self.sol.decode(self.sol.encode(strs))

    def test_basic(self):
        assert self.roundtrip(["hello", "world"]) == ["hello", "world"]

    def test_empty_list(self):
        assert self.roundtrip([]) == []

    def test_empty_string_in_list(self):
        assert self.roundtrip([""]) == [""]

    def test_multiple_empty_strings(self):
        assert self.roundtrip(["", "", ""]) == ["", "", ""]

    def test_string_containing_hash(self):
        # The key test: '#' inside a string must not break decoding
        assert self.roundtrip(["hel#lo", "world"]) == ["hel#lo", "world"]

    def test_string_containing_numbers_and_hash(self):
        assert self.roundtrip(["3#abc", "5#hello"]) == ["3#abc", "5#hello"]

    def test_single_char_strings(self):
        assert self.roundtrip(["a", "b", "c"]) == ["a", "b", "c"]

    def test_single_long_string(self):
        assert self.roundtrip(["a" * 1000]) == ["a" * 1000]

    def test_mixed_lengths(self):
        strs = ["short", "a", "much longer string", ""]
        assert self.roundtrip(strs) == strs
