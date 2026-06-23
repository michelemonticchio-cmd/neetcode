class SolutionSet:
    """
    Sliding window with a set mirroring the current window contents.
    When a duplicate is found, shrink from the left until it disappears.

    Time:  O(n) — each character added and removed at most once
    Space: O(min(n, alphabet))
    """
    def lengthOfLongestSubstring(self, s: str) -> int:
        window = set()
        left = 0
        maxLen = 0

        for right in range(len(s)):
            while s[right] in window:
                window.remove(s[left])
                left += 1
            window.add(s[right])
            maxLen = max(maxLen, right - left + 1)

        return maxLen


class Solution:
    """
    Sliding window with a dict storing the last seen index of each character.
    Jumps left directly to lastIndex+1 instead of removing one by one.

    Time:  O(n)
    Space: O(min(n, alphabet))
    """
    def lengthOfLongestSubstring(self, s: str) -> int:
        seen = {}
        left = 0
        maxLen = 0

        for right, c in enumerate(s):
            if c in seen and seen[c] >= left:
                left = seen[c] + 1      # jump past the duplicate
            seen[c] = right
            maxLen = max(maxLen, right - left + 1)

        return maxLen
