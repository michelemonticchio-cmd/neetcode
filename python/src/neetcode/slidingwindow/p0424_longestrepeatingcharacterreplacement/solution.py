class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        """
        Returns the length of the longest substring that can be made of
        a single repeated character after at most k replacements.

        Approach: variable-size sliding window with a frequency dict.
        A window is valid when (size - countMax) <= k, where countMax is
        the frequency of the most common character in the window.
        countMax is never decremented on shrink (stale value is safe —
        maxLen can only grow when a genuinely larger countMax appears).

        Time:  O(n)
        Space: O(1) — at most 26 keys in the dict
        """
        count = {}
        left = 0
        countMax = 0
        maxLen = 0

        for right in range(len(s)):
            c = s[right]
            count[c] = count.get(c, 0) + 1
            countMax = max(countMax, count[c])

            while (right - left + 1) - countMax > k:
                count[s[left]] -= 1
                left += 1

            maxLen = max(maxLen, right - left + 1)

        return maxLen
