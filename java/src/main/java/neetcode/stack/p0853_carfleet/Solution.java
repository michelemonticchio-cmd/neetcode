package neetcode.stack.p0853_carfleet;

import java.util.Arrays;
import java.util.Stack;

public class Solution {
    /**
     * Returns the number of car fleets that arrive at the target.
     *
     * Approach: sort cars by position descending (closest to target first),
     * compute each car's travel time, then use a stack to count fleets.
     * A car forms a new fleet if it takes longer than the car ahead of it
     * (it can never catch up). Otherwise it joins the fleet ahead.
     *
     * Time:  O(n log n) — dominated by sorting
     * Space: O(n) — for the cars array and the stack
     */
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;

        // Build [position, time_to_target] pairs
        double[][] cars = new double[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = (double)(target - position[i]) / speed[i];
        }

        // Sort by position descending: process cars closest to target first
        Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));

        Stack<Double> stack = new Stack<>();

        for (double[] car : cars) {
            double time = car[1];
            // A new fleet forms only if this car takes longer than the one ahead
            // (it can never catch the fleet in front)
            if (stack.isEmpty() || time > stack.peek()) {
                stack.push(time);
            }
            // Otherwise: this car catches the fleet ahead → absorbed, skip
        }

        return stack.size();
    }
}
