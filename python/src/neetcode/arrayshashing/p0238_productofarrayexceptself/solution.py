from typing import List


class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        """
        Returns an array where output[i] is the product of all elements
        except nums[i], without using division.

        Approach: two-pass prefix/suffix products.
        Pass 1 (left to right): output[i] = product of all elements before i.
        Pass 2 (right to left): multiply output[i] by product of all elements after i.

        Time:  O(n) — two linear passes
        Space: O(1) extra — output array is the required return value;
               only two scalar variables (prefix, suffix) are used
        """
        n = len(nums)
        output = [1] * n

        # Pass 1: prefix products
        prefix = 1
        for i in range(n):
            output[i] = prefix
            prefix *= nums[i]

        # Pass 2: suffix products multiplied into result
        suffix = 1
        for i in range(n - 1, -1, -1):
            output[i] *= suffix
            suffix *= nums[i]

        return output
