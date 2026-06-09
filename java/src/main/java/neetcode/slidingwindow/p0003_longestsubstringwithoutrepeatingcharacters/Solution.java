package neetcode.slidingwindow.p0003_longestsubstringwithoutrepeatingcharacters;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    /**
     * Returns the length of the longest substring of s that contains no
     * duplicate characters.
     *
     * Approach: sliding window with a HashSet mirror of the current window.
     * - Expand right by one character per iteration.
     * - When a duplicate enters, shrink from the left until the duplicate
     *   is removed, then add the new character.
     * - Track the maximum window length along the way.
     *
     * Time:  O(n) — each character is added and removed at most once
     * Space: O(min(n, alphabet))
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // Shrink from the left until c is no longer in the window
            while (window.contains(c)) {
                window.remove(s.charAt(left));
                left++;
            }

            window.add(c);

            int currentLen = right - left + 1;
            if (currentLen > maxLen) {
                maxLen = currentLen;
            }
        }

        return maxLen;
    }
}
