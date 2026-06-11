package neetcode.stack.p0084_largestrectangleinhistogram;

import java.util.Stack;

public class Solution {
    /**
     * Returns the area of the largest rectangle that fits inside the histogram.
     *
     * Approach: monotonic increasing stack of indices.
     * For each bar, the maximum rectangle using that bar's height extends
     * left to the first shorter bar and right to the first shorter bar.
     * When a shorter bar is encountered, all taller bars on the stack have
     * found their right boundary and their areas can be computed.
     *
     * A sentinel height of 0 at index n forces the stack to be fully
     * drained at the end without a separate post-loop pass.
     *
     * Time:  O(n) — each index is pushed and popped at most once
     * Space: O(n) — the stack holds at most n indices
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();  // indices, monotonically increasing by height

        for (int i = 0; i <= n; i++) {
            // Sentinel: treat index n as a bar of height 0 to flush the stack
            int currentHeight = (i == n) ? 0 : heights[i];

            // Pop all bars taller than currentHeight: they've found their right boundary
            while (!stack.isEmpty() && heights[stack.peek()] > currentHeight) {
                int height = heights[stack.pop()];
                // Left boundary: the new stack top (first bar shorter than popped bar)
                // Right boundary: current index i
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }
}
