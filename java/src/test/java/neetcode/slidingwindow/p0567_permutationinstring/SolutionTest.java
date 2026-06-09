package neetcode.slidingwindow.p0567_permutationinstring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolutionTest {

    private Solution sol;

    @BeforeEach
    void setUp() {
        sol = new Solution();
    }

    @Test
    void test_permutation_in_middle() {
        // "cab" is a permutation of "abc"
        assertTrue(sol.checkInclusion("abc", "lecabee"));
    }

    @Test
    void test_no_permutation() {
        assertFalse(sol.checkInclusion("abc", "lecaee"));
    }

    @Test
    void test_permutation_at_start() {
        // First window itself is a match
        assertTrue(sol.checkInclusion("ab", "abcd"));
    }

    @Test
    void test_permutation_at_end() {
        // Match only in the last window
        assertTrue(sol.checkInclusion("ab", "cdba"));
    }

    @Test
    void test_s1_longer_than_s2() {
        // Early exit: impossible to contain a permutation
        assertFalse(sol.checkInclusion("abcd", "ab"));
    }

    @Test
    void test_equal_length_match() {
        // s2 itself is a permutation of s1
        assertTrue(sol.checkInclusion("abc", "bca"));
    }

    @Test
    void test_equal_length_no_match() {
        assertFalse(sol.checkInclusion("abc", "xyz"));
    }

    @Test
    void test_repeated_characters() {
        // s1 = "aab" requires two 'a's — a HashSet would give the wrong answer here
        assertTrue(sol.checkInclusion("aab", "aabc"));
        assertFalse(sol.checkInclusion("aab", "xaby"));
    }

    @Test
    void test_single_character_match() {
        assertTrue(sol.checkInclusion("a", "b a c"));
        // Note: space is not lowercase letter but tests the general case
        assertTrue(sol.checkInclusion("a", "bac"));
    }

    @Test
    void test_all_same_characters() {
        assertTrue(sol.checkInclusion("aa", "aab"));
        assertFalse(sol.checkInclusion("aaa", "aab"));
    }
}
