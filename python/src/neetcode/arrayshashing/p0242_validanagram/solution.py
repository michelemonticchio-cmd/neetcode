from collections import Counter
from typing import Dict


class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        """
        Returns True if s and t are anagrams (same characters, same frequencies).

        Approach A (one-liner): compare Counter objects directly.
        Counter builds a frequency map in O(n); equality check is O(k)
        where k is the number of distinct characters.

        Approach B (explicit dict): increment counts for s, decrement for t.
        Short-circuits as soon as a count goes negative.

        Both are O(n) time and O(k) space where k <= alphabet size.
        """
        pass  # see implementations below


class SolutionCounter:
    """One-liner using Python's Counter."""

    def isAnagram(self, s: str, t: str) -> bool:
        return Counter(s) == Counter(t)


class SolutionDict:
    """Explicit frequency dict — mirrors the Java HashMap approach."""

    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t):
            return False

        count: Dict[str, int] = {}
        for c in s:
            count[c] = count.get(c, 0) + 1   # like map.getOrDefault(c, 0) + 1
        for c in t:
            count[c] = count.get(c, 0) - 1
            if count[c] < 0:
                return False

        return True
