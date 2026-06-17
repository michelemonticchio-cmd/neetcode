from typing import List


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        """
        Returns indices of the two numbers that add up to target.

        Approach: single pass with a HashMap (dict).
        For each number n at index i, compute diff = target - n.
        If diff is already in the map, we found the pair.
        Otherwise store n → i for future lookups.

        Time:  O(n) — one pass, O(1) dict lookup each step
        Space: O(n) — dict holds at most n entries
        """
        seen = {}                        # value → index
        for i, n in enumerate(nums):
            diff = target - n
            if diff in seen:
                return [seen[diff], i]
            seen[n] = i
        return []
