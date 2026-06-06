package neetcode.arraysandhashing.p0238_productofarrayexceptself;

public class Solution {
    /**
     * Returns an array where output[i] = product of all elements of nums except nums[i].
     * Does not use the division operator (handles zeros correctly).
     *
     * Approach: two passes accumulating prefix and suffix products in-place.
     *
     * Time:  O(n)
     * Space: O(1) extra (the output array is not counted)
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // First pass: result[i] = product of elements to the LEFT of i
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Second pass: multiply result[i] by the product of elements to the RIGHT of i
        int rightProd = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] = result[i] * rightProd;
            rightProd = rightProd * nums[i];
        }

        return result;
    }
}
