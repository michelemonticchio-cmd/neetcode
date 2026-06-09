package neetcode.slidingwindow.p0076_minimumwindowsubstring;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * Returns the shortest substring of s that contains every character
     * of t (including duplicates). Returns "" if no such substring exists.
     *
     * Approach: variable-size sliding window with two frequency maps.
     * - Expand right until the window contains all characters of t
     *   ("formed == required").
     * - Then shrink from the left to minimize the window, recording the
     *   best result each time the window is still valid.
     * - Repeat until right reaches the end of s.
     *
     * Time:  O(|s| + |t|)
     * Space: O(|t|) for the frequency maps
     */
    public String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) return "";

        // Frequency map for t
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // Frequency map for the current window
        Map<Character, Integer> window = new HashMap<>();

        int required = need.size();   // distinct chars in t that must be satisfied
        int formed = 0;               // distinct chars currently satisfied in window

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0;

        for (int right = 0; right < s.length(); right++) {
            // Add the new right character to the window
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);

            // Check if this char's frequency now satisfies t's requirement
            if (need.containsKey(c) && window.get(c).equals(need.get(c))) {
                formed++;
            }

            // Shrink from the left while the window is valid
            while (formed == required) {
                // Update best result if this window is smaller
                int windowLen = right - left + 1;
                if (windowLen < minLen) {
                    minLen = windowLen;
                    minLeft = left;
                }

                // Remove the leftmost character from the window
                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);
                if (need.containsKey(leftChar) &&
                    window.get(leftChar) < need.get(leftChar)) {
                    formed--;
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }
}
