from typing import List


class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        """
        Returns the 1-indexed positions of two numbers in a sorted array
        that sum to target.

        Approach: converging two pointers on a sorted array.
        If the sum is too small, advance left. If too large, retreat right.
        The sorted order guarantees we never miss the solution.

        Time:  O(n)
        Space: O(1)
        """
        left, right = 0, len(numbers) - 1

        while left < right:
            s = numbers[left] + numbers[right]
            if s == target:
                return [left + 1, right + 1]   # 1-indexed
            elif s < target:
                left += 1
            else:
                right -= 1

        return []
