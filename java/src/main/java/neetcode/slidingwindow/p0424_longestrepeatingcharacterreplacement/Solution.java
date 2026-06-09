package neetcode.slidingwindow.p0424_longestrepeatingcharacterreplacement;

public class Solution {
    /**
     * Returns the length of the longest substring that can be turned into
     * a single repeated character after at most k replacements.
     *
     * Approach: variable-size sliding window with a 26-letter frequency
     * counter. A window is valid when (windowSize - mostFrequentCount) <= k,
     * i.e. the number of characters we'd have to replace is within budget.
     *
     * Key trick: we don't update `countMax` when shrinking the window.
     * A stale (overestimated) countMax never causes a wrong answer because
     * maxLen can only grow when a genuinely larger countMax appears.
     *
     * Time:  O(n)
     * Space: O(1) — the count array has exactly 26 entries
     */
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0;
        int countMax = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            int idx = s.charAt(right) - 'A';
            count[idx]++;
            countMax = Math.max(countMax, count[idx]);

            // Window invalid: shrink from the left until it fits the budget k
            while ((right - left + 1) - countMax > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
