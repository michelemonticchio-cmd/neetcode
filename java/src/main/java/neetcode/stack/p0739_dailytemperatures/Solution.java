package neetcode.stack.p0739_dailytemperatures;

import java.util.Stack;

public class Solution {
    /**
     * For each day, returns the number of days until a warmer temperature.
     * Returns 0 if no warmer day exists.
     *
     * Approach: monotonic decreasing stack of indices.
     * The stack holds indices of days whose "next warmer day" has not been
     * found yet. When a new temperature is higher than the temperature at
     * the top index, that index has found its answer: the wait is
     * (current index - top index).
     *
     * Time:  O(n) — each index is pushed and popped at most once
     * Space: O(n) — worst case (decreasing sequence) all indices on stack
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];          // default 0 for days with no warmer future
        Stack<Integer> stack = new Stack<>();  // indices, monotonically decreasing by temperature

        for (int i = 0; i < n; i++) {
            // Pop all indices whose temperature is lower than today's
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int idx = stack.pop();
                result[idx] = i - idx;      // days waited = difference of indices
            }
            stack.push(i);
        }

        return result;
    }
}
