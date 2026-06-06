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

    ["abc", "defg"]  →  "3#abc4#defg"

**Encode** is a simple loop with `StringBuilder`.

**Decode** uses two pointers:
1. `i` points to the start of a length prefix.
2. Scan forward with `j` until reaching `#` — the substring `[i, j)` is the length.
3. Take exactly `length` characters after the `#`: that's the original string.
4. Move `i = j + 1 + length` to point to the next prefix.

Why this works: the `#` inside a payload is never ambiguous, because the decoder
never "searches" inside a payload — it skips ahead exactly `length` characters
based on the prefix.

## Complexity

- **Time:** O(N) for both encode and decode, where N is the sum of all string lengths
- **Space:** O(N) for the encoded string / decoded list

## Edge cases

- Empty list `[]` → encoded as `""`, decoded back to `[]`.
- List with empty string `[""]` → encoded as `"0#"`, decoded back to `[""]`.
- Strings containing `#` → handled correctly (the `#` is just a content byte
  once we're inside the payload).
- Strings containing numeric prefixes → also handled (e.g. `["12", "ab"]` →
  `"2#122#ab"`, which decodes unambiguously).

## Notes

This is **serialization**, not encryption. The word "encode" here means
"convert into a transmittable format", not "obscure the content". The output
is fully human-readable — the only goal is unambiguous reversibility.

The length-prefix pattern is everywhere in real-world systems: TCP packets,
HTTP `Content-Length`, Protocol Buffers, file formats like PNG and MP4 — all
use a length-prefixed structure to delimit variable-size payloads inside a
byte stream.

The key insight: **when you can't trust a delimiter (because the payload
might contain it), use a length prefix instead.**
