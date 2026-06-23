from collections import Counter

class Solution:
    def minWindow(self, s: str, t: str) -> str:
        """
        Returns the shortest substring of s containing all characters of t.

        Approach: variable-size sliding window with two dicts and a
        formed/required counter pair.
        - Expand right until all characters of t are satisfied (formed==required).
        - Shrink from left to minimize the window, recording the best result.
        - Repeat until right reaches the end.

        Time:  O(|s| + |t|)
        Space: O(|t|) — need and window hold at most |alphabet| keys
        """
        if not t or not s:
            return ""

        need = Counter(t)
        window = {}

        required = len(need)    # distinct chars in t that must be satisfied
        formed = 0              # distinct chars currently satisfied in window

        left = 0
        minLen = float("inf")
        minLeft = 0

        for right in range(len(s)):
            c = s[right]
            window[c] = window.get(c, 0) + 1

            # Check if this char's frequency now satisfies t's requirement
            if c in need and window[c] == need[c]:
                formed += 1

            # Shrink from left while window is valid
            while formed == required:
                if right - left + 1 < minLen:
                    minLen = right - left + 1
                    minLeft = left

                leftChar = s[left]
                window[leftChar] -= 1
                if leftChar in need and window[leftChar] < need[leftChar]:
                    formed -= 1
                left += 1

        return "" if minLen == float("inf") else s[minLeft:minLeft + minLen]
