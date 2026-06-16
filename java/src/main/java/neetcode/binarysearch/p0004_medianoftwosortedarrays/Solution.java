package neetcode.binarysearch.p0004_medianoftwosortedarrays;

public class Solution {
    /**
     * Returns the median of two sorted arrays in O(log(min(m, n))).
     *
     * Approach: binary search on the partition of the shorter array.
     * Instead of merging, find how many elements of nums1 belong in the
     * "left half" of the combined sorted array. The rest of the left half
     * comes from nums2. A partition is correct when neither array's left
     * boundary exceeds the other's right boundary. Binary search adjusts
     * the partition until it is correct.
     *
     * Sentinel values (MIN/MAX_VALUE) handle edge cases where a partition
     * index falls outside an array's bounds.
     *
     * Time:  O(log(min(m, n)))
     * Space: O(1) — the recursive swap call is tail-recursive in effect
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Always binary search on the shorter array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length, n = nums2.length;
        int half = (m + n) / 2;
        int left = 0, right = m;

        while (true) {
            int i = left + (right - left) / 2;  // elements from nums1 in left half
            int j = half - i;                    // elements from nums2 in left half

            int nums1Left  = (i > 0) ? nums1[i - 1] : Integer.MIN_VALUE;
            int nums1Right = (i < m) ? nums1[i]     : Integer.MAX_VALUE;
            int nums2Left  = (j > 0) ? nums2[j - 1] : Integer.MIN_VALUE;
            int nums2Right = (j < n) ? nums2[j]     : Integer.MAX_VALUE;

            if (nums1Left <= nums2Right && nums2Left <= nums1Right) {
                // Correct partition found
                if ((m + n) % 2 == 1) {
                    // Odd total: median is the smaller of the two right boundaries
                    return Math.min(nums1Right, nums2Right);
                } else {
                    // Even total: average of inner two elements
                    return (Math.max(nums1Left, nums2Left) +
                            Math.min(nums1Right, nums2Right)) / 2.0;
                }
            } else if (nums1Left > nums2Right) {
                right = i - 1;   // too many elements from nums1 on the left
            } else {
                left = i + 1;    // too few elements from nums1 on the left
            }
        }
    }
}
