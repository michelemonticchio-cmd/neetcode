from collections import Counter
from typing import List

class Solution:
    """
    Fixed-size sliding window using Counter comparison.
    Removes keys with count 0 to keep Counter comparison correct.

    Time:  O(n) — each character enters and leaves the window once
    Space: O(1) — at most 26 keys in each Counter
    """
    def checkInclusion(self, s1: str, s2: str) -> bool:
        if len(s1) > len(s2):
            return False

        count_s1 = Counter(s1)
        count_win = Counter(s2[:len(s1)])

        if count_s1 == count_win:
            return True

        for right in range(len(s1), len(s2)):
            count_win[s2[right]] += 1

            left_char = s2[right - len(s1)]
            count_win[left_char] -= 1
            if count_win[left_char] == 0:
                del count_win[left_char]      # avoid {char: 0} breaking equality

            if count_win == count_s1:
                return True

        return False


class SolutionArray:
    """
    Fixed-size sliding window using int[26] arrays — mirrors the Java solution.
    No key-cleanup needed: list == list compares element by element.

    Time:  O(n)
    Space: O(1) — two fixed arrays of 26 integers
    """
    def checkInclusion(self, s1: str, s2: str) -> bool:
        if len(s1) > len(s2):
            return False

        count_s1 = [0] * 26
        count_win = [0] * 26

        for c in s1:
            count_s1[ord(c) - ord('a')] += 1
        for c in s2[:len(s1)]:
            count_win[ord(c) - ord('a')] += 1

        if count_s1 == count_win:
            return True

        for right in range(len(s1), len(s2)):
            count_win[ord(s2[right]) - ord('a')] += 1
            count_win[ord(s2[right - len(s1)]) - ord('a')] -= 1
            if count_s1 == count_win:
                return True

        return False
