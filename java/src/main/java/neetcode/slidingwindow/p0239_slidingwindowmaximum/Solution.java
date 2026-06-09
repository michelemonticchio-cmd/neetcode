package neetcode.slidingwindow.p0239_slidingwindowmaximum;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    /**
     * Returns the maximum value in each sliding window of size k.
     *
     * Approach: monotonic decreasing deque of indices.
     * - The deque always holds indices whose corresponding values are in
     *   decreasing order from front to back.
     * - The front of the deque is always the index of the maximum in the
     *   current window.
     * - Before adding a new index, remove from the back all indices whose
     *   values are smaller than the new value (they can never be the max).
     * - Before recording the result, evict from the front any index that
     *   has slid out of the window (index < left).
     *
     * Time:  O(n) — each index is added and removed at most once
     * Space: O(k) — the deque holds at most k indices
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        int left = 0;
        int resultIdx = 0;

        for (int right = 0; right < n; right++) {
            // Remove from back: discard indices whose values are smaller
            // than nums[right] — they can never be the window maximum
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[right]) {
                deque.removeLast();
            }
            deque.addLast(right);

            // Remove from front: evict the index if it slid out of the window
            if (deque.peekFirst() < left) {
                deque.removeFirst();
            }

            // Window is fully formed: record the maximum (front of deque)
            if (right >= k - 1) {
                result[resultIdx++] = nums[deque.peekFirst()];
                left++;
            }
        }

        return result;
    }
}
