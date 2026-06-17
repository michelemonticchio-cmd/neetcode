from typing import List


class Solution:
    def hasDuplicate(self, nums: List[int]) -> bool:
        """
        Returns True if any value appears more than once in nums.

        Approach: convert to a set (which removes duplicates) and compare
        lengths. If the set is smaller than the original list, duplicates
        existed.

        Time:  O(n) — building the set requires one pass
        Space: O(n) — the set holds at most n distinct elements
        """
        return len(nums) != len(set(nums))
