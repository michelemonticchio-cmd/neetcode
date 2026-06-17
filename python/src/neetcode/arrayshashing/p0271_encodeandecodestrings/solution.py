from typing import List


class Solution:
    """
    Encodes a list of strings to a single string and decodes it back.

    Protocol: length-prefix encoding.
    Each string is prefixed with its length and a '#' separator:
        ["hello", "world"] → "5#hello5#world"
        ["hel#lo", "world"] → "6#hel#lo5#world"

    This avoids ambiguity with any character that might appear in the
    strings themselves, including '#'.

    encode Time:  O(n * k) — n strings of average length k
    decode Time:  O(n * k)
    Space:        O(n * k) for the encoded/decoded output
    """

    def encode(self, strs: List[str]) -> str:
        result = ""
        for s in strs:
            result += f"{len(s)}#{s}"   # e.g. "hello" → "5#hello"
        return result

    def decode(self, s: str) -> List[str]:
        result = []
        i = 0
        while i < len(s):
            j = i
            while s[j] != '#':         # scan to find the '#' separator
                j += 1
            length = int(s[i:j])       # parse the length prefix
            result.append(s[j + 1: j + 1 + length])  # extract exactly `length` chars
            i = j + 1 + length         # advance past this chunk
        return result
