from collections import deque
from typing import List

class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        """
        Returns the maximum value in each sliding window of size k.

        Approach: monotonic decreasing deque of indices.
        - Remove from the right all indices whose values are smaller than
          the current value (they can never be the window maximum).
        - Remove from the left if the front index has left the window.
        - The front of the deque is always the index of the current maximum.

        Time:  O(n) — each index is added and removed at most once
        Space: O(k) — the deque holds at most k indices
        """
        q = deque()    # indices, values decreasing left to right
        result = []

        for i in range(len(nums)):
            # Remove from right: smaller values can never be the maximum
            while q and nums[q[-1]] < nums[i]:
                q.pop()

            q.append(i)

            # Remove from left: index has slid out of the window
            if q[0] < i - k + 1:
                q.popleft()

            # Window is complete: record the maximum (front of deque)
            if i >= k - 1:
                result.append(nums[q[0]])

        return result
