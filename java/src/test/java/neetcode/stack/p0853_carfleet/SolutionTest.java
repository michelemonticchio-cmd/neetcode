package neetcode.stack.p0853_carfleet;

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
        // 3 fleets arrive: {10,8}, {5,3}, {0}
        assertEquals(3, sol.carFleet(12,
            new int[]{10, 8, 0, 5, 3},
            new int[]{2, 4, 1, 1, 3}));
    }

    @Test
    void test_single_car() {
        assertEquals(1, sol.carFleet(10,
            new int[]{0},
            new int[]{1}));
    }

    @Test
    void test_all_same_speed() {
        // All cars travel at the same speed → each is its own fleet
        assertEquals(3, sol.carFleet(10,
            new int[]{1, 2, 3},
            new int[]{1, 1, 1}));
    }

    @Test
    void test_all_merge_into_one() {
        // The slowest car is at the front — everyone catches up
        assertEquals(1, sol.carFleet(100,
            new int[]{0, 10, 20},
            new int[]{10, 10, 1}));
    }

    @Test
    void test_no_catching_up() {
        // Each car is faster than the one ahead but can never catch it
        // (front car is already very close)
        assertEquals(3, sol.carFleet(10,
            new int[]{4, 2, 0},
            new int[]{1, 2, 4}));
    }

    @Test
    void test_cars_at_same_position() {
        // Two cars at different positions, slower one is ahead
        assertEquals(1, sol.carFleet(10,
            new int[]{6, 8},
            new int[]{3, 2}));
    }

    @Test
    void test_two_cars_no_merge() {
        // Front car is faster → they never meet
        assertEquals(2, sol.carFleet(10,
            new int[]{3, 7},
            new int[]{3, 2}));
    }
}
