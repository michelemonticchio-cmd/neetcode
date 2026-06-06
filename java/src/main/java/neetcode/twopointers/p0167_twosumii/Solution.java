package neetcode.twopointers.p0167_twosumii;

public class Solution {
    /**
     * Returns the 1-indexed positions of two numbers in a sorted array
     * that sum up to the given target.
     *
     * Approach: converging two pointers on a sorted array.
     * - i starts at the left, j at the right end.
     * - If the sum is too small, advance i (need a larger number).
     * - If the sum is too large, decrement j (need a smaller number).
     * - If equal, we've found the answer.
     *
     * Time:  O(n)
     * Space: O(1) — only two index variables, as required by the problem
     */
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;

        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                int[] sol = new int[2];
                sol[0] = i + 1;          // 1-indexed
                sol[1] = j + 1;          // 1-indexed
                return sol;
            } else if (numbers[i] + numbers[j] < target) {
                i++;                     // sum too small → need bigger left
            } else {
                j--;                     // sum too big → need smaller right
            }
        }
        // Unreachable: the problem guarantees a unique solution exists.
        // Required by the compiler to make every path return a value.
        return new int[]{-1, -1};
    }
}
