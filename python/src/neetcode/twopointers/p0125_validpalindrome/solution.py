class Solution:
    """One-liner using Python string tools."""

    def isPalindrome(self, s: str) -> bool:
        """
        Cleans the string (keep only alphanumeric, lowercase),
        then checks if it equals its reverse.

        Time:  O(n)
        Space: O(n) — the cleaned string
        """
        cleaned = "".join(c.lower() for c in s if c.isalnum())
        return cleaned == cleaned[::-1]


class SolutionTwoPointers:
    """Explicit two-pointer approach — O(1) space, mirrors the Java solution."""

    def isPalindrome(self, s: str) -> bool:
        """
        Converging two pointers, skipping non-alphanumeric characters in place.

        Time:  O(n)
        Space: O(1) — no auxiliary string
        """
        left, right = 0, len(s) - 1

        while left < right:
            while left < right and not s[left].isalnum():
                left += 1
            while left < right and not s[right].isalnum():
                right -= 1
            if s[left].lower() != s[right].lower():
                return False
            left += 1
            right -= 1

        return True
