# NeetCode 150

My solutions to the [NeetCode 150](https://neetcode.io/practice) problems, organized by category.

## Structure

Each problem lives in its own package with:
- `Solution.java` — commented solution
- `README.md` — problem analysis, approach, complexity

Tests live under `src/test/java/` mirroring the same package structure.

## Progress

**Total: 0 / 150**

### Arrays & Hashing (0/9)
- [ ] [217. Contains Duplicate](./src/main/java/neetcode/arraysandhashing/p0217_containsduplicate/)
- [ ] [242. Valid Anagram](./src/main/java/neetcode/arraysandhashing/p0242_validanagram/)
- [ ] [1. Two Sum](./src/main/java/neetcode/arraysandhashing/p0001_twosum/)
- [ ] [49. Group Anagrams](./src/main/java/neetcode/arraysandhashing/p0049_groupanagrams/)
- [ ] [347. Top K Frequent Elements](./src/main/java/neetcode/arraysandhashing/p0347_topkfrequentelements/)
- [ ] [271. Encode and Decode Strings](./src/main/java/neetcode/arraysandhashing/p0271_encodeanddecodestrings/)
- [ ] [238. Product of Array Except Self](./src/main/java/neetcode/arraysandhashing/p0238_productofarrayexceptself/)
- [ ] [36. Valid Sudoku](./src/main/java/neetcode/arraysandhashing/p0036_validsudoku/)
- [ ] [128. Longest Consecutive Sequence](./src/main/java/neetcode/arraysandhashing/p0128_longestconsecutivesequence/)

### Two Pointers (0/5)
- [ ] [125. Valid Palindrome](./src/main/java/neetcode/twopointers/p0125_validpalindrome/)
- [ ] [167. Two Sum II](./src/main/java/neetcode/twopointers/p0167_twosumii/)
- [ ] [15. 3Sum](./src/main/java/neetcode/twopointers/p0015_threesum/)
- [ ] [11. Container With Most Water](./src/main/java/neetcode/twopointers/p0011_containerwithmostwater/)
- [ ] [42. Trapping Rain Water](./src/main/java/neetcode/twopointers/p0042_trappingrainwater/)

<!-- ... other categories ... -->

## Setup

\`\`\`bash
# Requirements
JDK 17+
Maven 3.8+

# Run all tests
mvn test

# Run tests for a specific problem
mvn test -Dtest=SolutionTest#test_basic -pl . -am
mvn test -Dtest="neetcode.arraysandhashing.p0001_twosum.SolutionTest"
\`\`\`
