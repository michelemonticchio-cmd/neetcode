package neetcode.stack.p0150_evaluatereversepolishnotation;

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
    void test_addition_then_multiply() {
        // (2 + 1) * 3 = 9
        assertEquals(9, sol.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
    }

    @Test
    void test_division_then_addition() {
        // 4 + (13 / 5) = 4 + 2 = 6
        assertEquals(6, sol.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }

    @Test
    void test_complex_expression() {
        // ((10 * (6 / ((9 + 3) * -11))) + 17) + 5 = 22
        assertEquals(22, sol.evalRPN(new String[]{
            "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"
        }));
    }

    @Test
    void test_single_number() {
        assertEquals(42, sol.evalRPN(new String[]{"42"}));
    }

    @Test
    void test_subtraction_order() {
        // 5 - 3 = 2, not 3 - 5 = -2
        assertEquals(2, sol.evalRPN(new String[]{"5", "3", "-"}));
    }

    @Test
    void test_division_order() {
        // 10 / 2 = 5, not 2 / 10 = 0
        assertEquals(5, sol.evalRPN(new String[]{"10", "2", "/"}));
    }

    @Test
    void test_division_truncates_toward_zero() {
        // 7 / 2 = 3 (truncates, not 3.5)
        assertEquals(3, sol.evalRPN(new String[]{"7", "2", "/"}));
        // -7 / 2 = -3 (toward zero, not -4)
        assertEquals(-3, sol.evalRPN(new String[]{"-7", "2", "/"}));
    }

    @Test
    void test_negative_numbers() {
        // -3 + -2 = -5
        assertEquals(-5, sol.evalRPN(new String[]{"-3", "-2", "+"}));
    }
}
