package neetcode.stack.p0155_minstack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinStackTest {

    private MinStack ms;

    @BeforeEach
    void setUp() {
        ms = new MinStack();
    }

    @Test
    void test_basic_sequence() {
        ms.push(5);
        ms.push(3);
        ms.push(7);
        ms.push(2);
        assertEquals(2, ms.getMin());
        assertEquals(2, ms.top());
        ms.pop();
        assertEquals(3, ms.getMin());   // 2 is gone, min is now 3
        assertEquals(7, ms.top());
    }

    @Test
    void test_min_after_pop() {
        ms.push(1);
        ms.push(2);
        assertEquals(1, ms.getMin());
        ms.pop();
        assertEquals(1, ms.getMin());   // 1 is still the bottom element
    }

    @Test
    void test_push_same_value() {
        // Duplicate minimums: both should be tracked
        ms.push(3);
        ms.push(3);
        assertEquals(3, ms.getMin());
        ms.pop();
        assertEquals(3, ms.getMin());   // still 3 after one pop
    }

    @Test
    void test_single_element() {
        ms.push(42);
        assertEquals(42, ms.top());
        assertEquals(42, ms.getMin());
        ms.pop();
    }

    @Test
    void test_negative_numbers() {
        ms.push(0);
        ms.push(-1);
        ms.push(-3);
        assertEquals(-3, ms.getMin());
        ms.pop();
        assertEquals(-1, ms.getMin());
    }

    @Test
    void test_min_not_at_top() {
        // The minimum is buried in the middle, not at the top
        ms.push(5);
        ms.push(1);
        ms.push(4);
        assertEquals(1, ms.getMin());   // 1 is in the middle
        assertEquals(4, ms.top());      // top is 4
    }

    @Test
    void test_decreasing_sequence() {
        ms.push(5);
        ms.push(4);
        ms.push(3);
        ms.push(2);
        ms.push(1);
        for (int expected = 1; expected <= 5; expected++) {
            assertEquals(expected, ms.getMin());
            ms.pop();
        }
    }
}
