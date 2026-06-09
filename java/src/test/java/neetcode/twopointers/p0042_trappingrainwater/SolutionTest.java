package neetcode.twopointers.p0042_trappingrainwater;

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
    void test_classic_leetcode_example() {
        // The canonical [0,1,0,2,1,0,1,3,2,1,2,1] traps 6 units of water
        assertEquals(6, sol.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    @Test
    void test_traced_example() {
        // [0,2,0,3,1,0,1,3,2,1] traps 9 units
        assertEquals(9, sol.trap(new int[]{0, 2, 0, 3, 1, 0, 1, 3, 2, 1}));
    }

    @Test
    void test_neetcode_simple_example() {
        // [4,2,0,3,2,5] traps 9 units
        assertEquals(9, sol.trap(new int[]{4, 2, 0, 3, 2, 5}));
    }

    @Test
    void test_no_water() {
        // Monotonically decreasing → no walls to trap water
        assertEquals(0, sol.trap(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    void test_monotonically_increasing() {
        // No trapping either — water would spill off the right
        assertEquals(0, sol.trap(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void test_water_over_intermediate_wall() {
        // [3, 0, 1, 0, 3] → tests the "water sits on intermediate walls" property
        // Column 1: min(3,3) - 0 = 3
        // Column 2: min(3,3) - 1 = 2
        // Column 3: min(3,3) - 0 = 3
        // Total: 8
        assertEquals(8, sol.trap(new int[]{3, 0, 1, 0, 3}));
    }

    @Test
    void test_empty_array() {
        assertEquals(0, sol.trap(new int[]{}));
    }

    @Test
    void test_single_bar() {
        assertEquals(0, sol.trap(new int[]{5}));
    }

    @Test
    void test_two_bars() {
        // No middle → no trapping possible
        assertEquals(0, sol.trap(new int[]{5, 3}));
    }

    @Test
    void test_flat_terrain() {
        // All equal → no trapping
        assertEquals(0, sol.trap(new int[]{3, 3, 3, 3}));
    }

    @Test
    void test_single_valley() {
        // [5, 0, 5] traps 5 units
        assertEquals(5, sol.trap(new int[]{5, 0, 5}));
    }
}
