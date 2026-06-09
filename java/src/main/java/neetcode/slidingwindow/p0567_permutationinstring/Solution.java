package neetcode.slidingwindow.p0567_permutationinstring;

import java.util.Arrays;

public class Solution {

    /**
     * Returns true if any permutation of s1 exists as a contiguous
     * substring of s2.
     *
     * Approach: fixed-size sliding window of length s1.length().
     * A window is a permutation of s1 iff their character frequency
     * arrays are identical. Maintain a frequency array for the current
     * window: add the incoming character on the right, remove the
     * outgoing character on the left, compare with s1's array.
     *
     * Time:  O(n) where n = s2.length() — each character is visited once;
     *        the array comparison is O(26) = O(1)
     * Space: O(1) — two fixed 26-entry arrays
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] countS1 = new int[26];
        int[] countWin = new int[26];
        int len = s1.length();

        // Initialize both frequency arrays with the first window
        for (int i = 0; i < len; i++) {
            countS1[s1.charAt(i) - 'a']++;
            countWin[s2.charAt(i) - 'a']++;
        }

        // Check the first window
        if (Arrays.equals(countS1, countWin)) return true;

        // Slide the window one step at a time
        for (int right = len; right < s2.length(); right++) {
            countWin[s2.charAt(right) - 'a']++;           // new char enters
            countWin[s2.charAt(right - len) - 'a']--;     // old char leaves
            if (Arrays.equals(countS1, countWin)) return true;
        }

        return false;
    }
}
