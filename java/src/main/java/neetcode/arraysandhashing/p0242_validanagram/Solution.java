package neetcode.arraysandhashing.p0242_validanagram;

public class Solution {
    /**
     * Returns true if t is an anagram of s.
     * Assumes both strings contain only lowercase English letters.
     *
     * Time:  O(n)  — single pass over the strings
     * Space: O(1)  — fixed-size count array of 26 elements
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }

        return true;
    }
}
