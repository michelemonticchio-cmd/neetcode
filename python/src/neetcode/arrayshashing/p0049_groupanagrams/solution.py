from collections import defaultdict
from typing import List


class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        """
        Groups strings that are anagrams of each other.

        Approach: use the sorted word as a HashMap key.
        Two words are anagrams iff they produce the same string when
        their characters are sorted. A defaultdict(list) collects all
        words sharing the same key into the same group.

        Time:  O(n * k log k) — n words, each sorted in O(k log k)
               where k is the length of the longest word
        Space: O(n * k) — the map stores all words
        """
        groups = defaultdict(list)
        for word in strs:
            key = "".join(sorted(word))   # canonical form of the anagram group
            groups[key].append(word)
        return list(groups.values())
