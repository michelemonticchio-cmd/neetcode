from typing import List


class Solution:
    def maxArea(self, heights: List[int]) -> int:
        """
        Returns the maximum water that can be contained between two bars.

        Approach: converging two pointers.
        At each step compute the area bounded by the current pair, then
        move the pointer with the shorter bar — it is the bottleneck and
        keeping it fixed can never improve the area.

        Time:  O(n)
        Space: O(1)
        """
        left, right = 0, len(heights) - 1
        maxA = 0

        while left < right:
            area = min(heights[left], heights[right]) * (right - left)
            maxA = max(maxA, area)

            if heights[left] < heights[right]:
                left += 1
            else:
                right -= 1

        return maxA
