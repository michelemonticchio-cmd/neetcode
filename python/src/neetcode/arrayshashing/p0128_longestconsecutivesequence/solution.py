from typing import List


class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        """
        Returns the length of the longest consecutive sequence in nums.

        Approach: HashSet + sequence-start detection.
        A number n is the start of a sequence only if (n-1) is not in the set.
        From each start, count how far the sequence extends.
        Every element is visited at most twice → O(n) total.

        Time:  O(n) — set construction + amortized linear scan
        Space: O(n) — the set holds all elements
        """
        s = set(nums)
        maxLen = 0

        for n in nums:
            if (n - 1) not in s:        # n is the start of a sequence
                length = 1
                while (n + length) in s:    # extend the sequence
                    length += 1
                maxLen = max(maxLen, length)

        return maxLen
