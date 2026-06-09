package neetcode.slidingwindow.p0003_longestsubstringwithoutrepeatingcharacters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    private Solution sol;

    @BeforeEach
    void setUp() {
        sol = new Solution();
    }

    @Test
    void test_neetcode_example_one() {
        // "zxyzxyz" → "xyz" or "yzx" etc., length 3
        assertEquals(3, sol.lengthOfLongestSubstring("zxyzxyz"));
    }

    @Test
    void test_neetcode_example_two() {
        // "xxxx" → just "x", length 1
        assertEquals(1, sol.lengthOfLongestSubstring("xxxx"));
    }

    @Test
    void test_leetcode_classic() {
        // "abcabcbb" → "abc", length 3
        assertEquals(3, sol.lengthOfLongestSubstring("abcabcbb"));
    }

    @Test
    void test_all_unique() {
        // "abcdef" → whole string is the longest, length 6
        assertEquals(6, sol.lengthOfLongestSubstring("abcdef"));
    }

    @Test
    void test_empty_string() {
        assertEquals(0, sol.lengthOfLongestSubstring(""));
    }

    @Test
    void test_single_char() {
        assertEquals(1, sol.lengthOfLongestSubstring("a"));
    }

    @Test
    void test_pwwkew() {
        // Classic LeetCode example: longest is "wke", length 3
        // ("wkew" would be wrong because it has duplicate 'w')
        assertEquals(3, sol.lengthOfLongestSubstring("pwwkew"));
    }

    @Test
    void test_with_spaces_and_symbols() {
        // " " (single space) → length 1
        assertEquals(1, sol.lengthOfLongestSubstring(" "));
        // "ab c" → all 4 unique
        assertEquals(4, sol.lengthOfLongestSubstring("ab c"));
    }

    @Test
    void test_long_repeating_pattern() {
        // "dvdf" → "vdf", length 3
        // This catches a bug where left is advanced incorrectly past the duplicate
        assertEquals(3, sol.lengthOfLongestSubstring("dvdf"));
    }

    @Test
    void test_alternating_duplicates() {
        // "abba" → after the second 'b' the window shrinks; longest is "ab" or "ba", length 2
        // This catches a bug where left jumps too far back
        assertEquals(2, sol.lengthOfLongestSubstring("abba"));
    }
}
