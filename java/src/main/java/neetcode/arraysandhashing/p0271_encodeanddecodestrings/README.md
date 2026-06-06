# 271. Encode and Decode Strings

🔗 [LeetCode](https://leetcode.com/problems/encode-and-decode-strings/) | 📺 [NeetCode](https://neetcode.io/problems/string-encode-and-decode)

**Difficulty:** Medium
**Category:** Arrays & Hashing

## Problem

Design `encode` and `decode` methods such that:
- `encode(List<String>) → String` serializes a list of strings into a single string.
- `decode(String) → List<String>` reconstructs the original list from that string.

Strings may contain **any** of the 256 ASCII characters, so no naive separator
will work — the input might contain whatever character you pick as a delimiter.

## Approach

**Length-prefix encoding.** For each string, write its length, a delimiter `#`,
then the string itself. Concatenate everything.
