package neetcode.twopointers.p0011_containerwithmostwater;

public class Solution {
    /**
     * Returns the maximum amount of water that can be contained between
     * two vertical lines from the given heights array.
     *
     * Approach: converging two pointers. At each step compute the area
     * bounded by the current pair, then advance the pointer with the
     * shorter line — it's the bottleneck, so moving it is the only move
     * that can possibly improve the area.
     *
     * Time:  O(n)
     * Space: O(1)
     */
    public int maxArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int maxTot = 0;

        while (left < right) {
            int height = Math.min(heights[left], heights[right]);
            int width  = right - left;
            maxTot = Math.max(maxTot, height * width);

            // Move the pointer with the shorter line: the area is capped
            // by it, so moving the taller side can never produce a larger area
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxTot;
    }
}
