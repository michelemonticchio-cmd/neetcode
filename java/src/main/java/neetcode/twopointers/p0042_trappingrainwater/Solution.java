package neetcode.twopointers.p0042_trappingrainwater;

public class Solution {
    /**
     * Returns the total amount of water that can be trapped after raining,
     * given an elevation map where each bar has width 1.
     *
     * Approach: converging two pointers maintaining the max seen on each side.
     * The key insight is that we only need to know one side's max precisely
     * to compute the water at the current cell — the other side is guaranteed
     * to have a taller (or equal) wall by the pointer comparison itself.
     *
     * Time:  O(n)
     * Space: O(1)
     */
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int total = 0;

        while (left < right) {
            // Process the shorter side: its max is the binding constraint
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];        // new tallest wall on the left
                } else {
                    total += leftMax - height[left];   // water sits above this cell
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    total += rightMax - height[right];
                }
                right--;
            }
        }
        return total;
    }
}
