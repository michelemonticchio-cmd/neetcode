package neetcode.slidingwindow.p0424_longestrepeatingcharacterreplacement;

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
        // "XYYX", k=2 → can replace both Xs (or both Ys) → "YYYY" → length 4
        assertEquals(4, sol.characterReplacement("XYYX", 2));
    }

    @Test
    void test_neetcode_example_two() {
        // "AAABABB", k=1 → replace one B in "AAABA" → "AAAAA" → length 5
        assertEquals(5, sol.characterReplacement("AAABABB", 1));
    }

    @Test
    void test_leetcode_classic() {
        // "ABAB", k=2 → swap both Bs to As → "AAAA" → length 4
        assertEquals(4, sol.characterReplacement("ABAB", 2));
    }

    @Test
    void test_zero_replacements() {
        // k=0 → no replacements allowed; longest run of same char
        // "ABBBC" → "BBB" → length 3
        assertEquals(3, sol.characterReplacement("ABBBC", 0));
    }

    @Test
    void test_single_character() {
        assertEquals(1, sol.characterReplacement("A", 5));
    }

    @Test
    void test_all_same() {
        // No replacements needed; whole string is valid
        assertEquals(7, sol.characterReplacement("AAAAAAA", 2));
    }

    @Test
    void test_k_larger_than_string() {
        // k >= n-1 means we can always turn the whole string into one char
        assertEquals(5, sol.characterReplacement("ABCDE", 10));
    }

    @Test
    void test_alternating_pattern() {
        // "ABABBA", k=2 → e.g. "BABBBA" → "BBBBBA" — best is length 5 ("ABBBA" with 1 swap)
        // Actually: with k=2 we can pick window "ABABBA" (size 6), counts A=3, B=3,
        // need = 6 - 3 = 3 > 2 → invalid. So max valid is 5: e.g. "ABABB" (need 2)
        assertEquals(5, sol.characterReplacement("ABABBA", 2));
    }

    @Test
    void test_long_with_many_replacements() {
        // "AABABBA", k=1 → "AABABBA" need = 7 - 4 = 3 > 1 invalid
        // Best: "AABAB" (need 2, no), "AABA" (need 1, length 4)
        // Actually the answer is 4 here
        assertEquals(4, sol.characterReplacement("AABABBA", 1));
    }
}
