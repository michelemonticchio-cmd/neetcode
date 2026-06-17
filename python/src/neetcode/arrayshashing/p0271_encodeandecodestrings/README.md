# 271. Encode and Decode Strings — Python

🔗 [LeetCode](https://leetcode.com/problems/encode-and-decode-strings/) | 📺 [NeetCode](https://neetcode.io/problems/string-encode-and-decode)

**Difficulty:** Medium
**Category:** Arrays & Hashing

## Problem

Design an algorithm to encode a list of strings into a single string, and
decode it back to the original list. The encoded string must be transmittable
over a network and decoded correctly on the other side.

## Why a simple delimiter doesn't work

Using a single character like `#` as a separator breaks when the character
appears inside a string:

    ["hel#lo", "world"] → "hel#lo#world"
    decode → ["hel", "lo", "world"]  ❌ wrong!

## Approach: length-prefix encoding

Prefix each string with its length followed by `#`:

    ["hello", "world"]   → "5#hello5#world"
    ["hel#lo", "world"]  → "6#hel#lo5#world"

Decoding reads the length prefix, then extracts exactly that many characters
— no ambiguity regardless of the string content.

### Encode

    for s in strs:
        result += f"{len(s)}#{s}"

### Decode

    while i < len(s):
        j = i
        while s[j] != '#':
            j += 1
        length = int(s[i:j])
        result.append(s[j+1 : j+1+length])
        i = j + 1 + length

## Complexity

- **Time:** O(n·k) for both encode and decode
- **Space:** O(n·k) for the output

## Python tools used

- **f-string** `f"{len(s)}#{s}"` — string interpolation, like
  `String.format` in Java.
- **Slicing** `s[i:j]` — substring from index `i` (inclusive) to `j`
  (exclusive), like `s.substring(i, j)` in Java.
- **`int(s[i:j])`** — parse string to integer, like `Integer.parseInt()`
  in Java.

## Java vs Python

    // Java encode
    StringBuilder sb = new StringBuilder();
    for (String s : strs)
        sb.append(s.length()).append('#').append(s);

    # Python encode
    result = ""
    for s in strs:
        result += f"{len(s)}#{s}"

    // Java decode
    int i = 0;
    while (i < s.length()) {
        int j = s.indexOf('#', i);
        int len = Integer.parseInt(s.substring(i, j));
        result.add(s.substring(j + 1, j + 1 + len));
        i = j + 1 + len;
    }

    # Python decode
    while i < len(s):
        j = i
        while s[j] != '#': j += 1
        length = int(s[i:j])
        result.append(s[j+1:j+1+length])
        i = j + 1 + length
