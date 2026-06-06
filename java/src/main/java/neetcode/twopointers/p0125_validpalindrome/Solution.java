package neetcode.twopointers.p0125_validpalindrome;

public class Solution {
    /**
     * Returns true if the string is a palindrome, considering only alphanumeric
     * characters and ignoring case.
     *
     * Approach: two pointers converging from both ends, skipping
     * non-alphanumeric characters in-place. No string allocation needed.
     *
     * Time:  O(n)
     * Space: O(1)
     */
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            // Skip non-alphanumeric characters from the left
            while (i < j && !Character.isLetterOrDigit(s.charAt(i)))  i++;
            // Skip non-alphanumeric characters from the right
            while (i < j && !Character.isLetterOrDigit(s.charAt(j)))  j--;
            // Compare case-insensitive
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
