package neetcode.slidingwindow.p0076_minimumwindowsubstring;

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
    void test_neetcode_example() {
        assertEquals("YXAZ", sol.minWindow("OUZODYXAZV", "XYZ"));
    }

    @Test
    void test_classic_leetcode_example() {
        assertEquals("BANC", sol.minWindow("ADOBECODEBANC", "ABC"));
    }

    @Test
    void test_single_char_match() {
        assertEquals("a", sol.minWindow("a", "a"));
    }

    @Test
    void test_no_valid_window() {
        // t has a character not in s
        assertEquals("", sol.minWindow("a", "aa"));
    }

    @Test
    void test_t_longer_than_s() {
        assertEquals("", sol.minWindow("ab", "abc"));
    }

    @Test
    void test_whole_string_is_minimum() {
        // Every character of t appears exactly once in s, spread across it
        assertEquals("AECBAD", sol.minWindow("AECBAD", "ABCDE"));
    }

    @Test
    void test_duplicates_in_t() {
        // t = "aa" requires at least two 'a's in the window
        assertEquals("aa", sol.minWindow("aa", "aa"));
        assertEquals("", sol.minWindow("a", "aa"));
        assertEquals("baa", sol.minWindow("baabc", "aa"));
    }

    @Test
    void test_multiple_valid_windows() {
        // "ab" and "ba" are both valid but "ab" (len 2) beats "abc" (len 3)
        // s = "abc", t = "ac" → minimum is "abc" (length 3) wait:
        // actually "a" is at 0, "c" is at 2, so minimum window is "abc" length 3
        assertEquals("abc", sol.minWindow("abc", "ac"));
    }

    @Test
    void test_empty_s() {
        assertEquals("", sol.minWindow("", "a"));
    }

    @Test
    void test_empty_t() {
        assertEquals("", sol.minWindow("abc", ""));
    }
}
