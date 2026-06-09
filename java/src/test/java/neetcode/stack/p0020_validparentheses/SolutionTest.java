package neetcode.stack.p0020_validparentheses;

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
    void test_simple_valid() {
        assertTrue(sol.isValid("()"));
        assertTrue(sol.isValid("[]"));
        assertTrue(sol.isValid("{}"));
    }

    @Test
    void test_nested_valid() {
        assertTrue(sol.isValid("([{}])"));
        assertTrue(sol.isValid("{[()]}"));
    }

    @Test
    void test_sequential_valid() {
        assertTrue(sol.isValid("()[]{}"));
    }

    @Test
    void test_wrong_order() {
        // ([)] is invalid: ) tries to close ( but [ is still open
        assertFalse(sol.isValid("([)]"));
    }

    @Test
    void test_unclosed_opener() {
        // Stack non-empty at end → false
        assertFalse(sol.isValid("((("));
        assertFalse(sol.isValid("([]"));
    }

    @Test
    void test_closer_without_opener() {
        // Stack empty when closer arrives → false
        assertFalse(sol.isValid(")"));
        assertFalse(sol.isValid("()]"));
    }

    @Test
    void test_empty_string() {
        // Empty string → no brackets → valid
        assertTrue(sol.isValid(""));
    }

    @Test
    void test_single_opener() {
        assertFalse(sol.isValid("("));
    }

    @Test
    void test_mismatched_types() {
        assertFalse(sol.isValid("(]"));
        assertFalse(sol.isValid("{)"));
    }
}
