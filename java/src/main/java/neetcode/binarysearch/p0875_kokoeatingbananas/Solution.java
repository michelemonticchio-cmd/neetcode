package neetcode.binarysearch.p0875_kokoeatingbananas;

import java.util.Arrays;

public class Solution {
    /**
     * Returns the minimum eating speed k such that all piles can be
     * consumed within h hours.
     *
     * Approach: binary search on the answer.
     * For a given speed k, the hours needed for a pile of size p is
     * ceil(p / k) = (p + k - 1) / k. The total hours needed is monotonically
     * decreasing in k: a larger k never requires more hours. We binary
     * search for the smallest k where totalHours(k) <= h.
     *
     * Search space: k in [1, max(piles)]. A speed larger than the biggest
     * pile is never useful (any pile finishes in 1 hour at that speed).
     *
     * Time:  O(n * log(max(piles))) — each of the O(log(max)) binary search
     *        steps computes totalHours in O(n)
     * Space: O(1)
     */
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = Arrays.stream(piles).max().orElseThrow();

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int hours = 0;
            for (int pile : piles) {
                hours += (pile + mid - 1) / mid;  // ceiling division
            }

            if (hours <= h) {
                right = mid - 1;  // mid works; try a smaller (slower) speed
            } else {
                left = mid + 1;   // mid too slow; need a faster speed
            }
        }

        return left;
    }
}
