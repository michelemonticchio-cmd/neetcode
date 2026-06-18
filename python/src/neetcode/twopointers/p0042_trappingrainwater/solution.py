from typing import List


class Solution:
    def trap(self, height: List[int]) -> int:
        """
        Returns the total water trapped after raining.

        Approach: converging two pointers with running max on each side.
        Process the side with the shorter current bar — its max is the
        binding constraint. Update the running max or accumulate water.

        Time:  O(n)
        Space: O(1)
        """
        left, right = 0, len(height) - 1
        leftMax, rightMax = 0, 0
        total = 0

        while left < right:
            if height[left] < height[right]:
                if height[left] >= leftMax:
                    leftMax = height[left]
                else:
                    total += leftMax - height[left]
                left += 1
            else:
                if height[right] >= rightMax:
                    rightMax = height[right]
                else:
                    total += rightMax - height[right]
                right -= 1

        return total
