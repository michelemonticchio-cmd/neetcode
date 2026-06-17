# 49. Group Anagrams — Python

🔗 [LeetCode](https://leetcode.com/problems/group-anagrams/) | 📺 [NeetCode](https://neetcode.io/problems/anagram-groups)

**Difficulty:** Medium
**Category:** Arrays & Hashing

## Problem

Given an array of strings `strs`, group all anagrams together and return
the groups in any order.

## Key insight

Two words are anagrams if and only if their characters, when sorted
alphabetically, produce the same string:

    "eat" → sorted → "aet"
    "tea" → sorted → "aet"   ← same key → same group
    "tan" → sorted → "ant"   ← different key → different group

## Approach

**Sorted word as HashMap key.**

Use a `defaultdict(list)` to collect words by their sorted form:

    for word in strs:
        key = "".join(sorted(word))
        groups[key].append(word)

`defaultdict(list)` initializes every new key with an empty list
automatically — no `if key not in groups` check needed.

## Complexity

- **Time:** O(n · k log k) — n words, each sorted in O(k log k)
- **Space:** O(n · k) — the map stores all words

## Java vs Python

    // Java
    Map<String, List<String>> groups = new HashMap<>();
    for (String word : strs) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        String key = new String(chars);
        groups.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
    }

    # Python
    groups = defaultdict(list)
    for word in strs:
        key = "".join(sorted(word))
        groups[key].append(word)

Key differences:
- `defaultdict(list)` replaces `computeIfAbsent`
- `"".join(sorted(word))` replaces `Arrays.sort` + `new String(chars)`
- Python strings are directly iterable — no `.toCharArray()` needed

## Notes

`defaultdict(list)` from `collections` is the idiomatic Python replacement
for the Java `getOrDefault` / `computeIfAbsent` pattern whenever you need
to group items into lists.
