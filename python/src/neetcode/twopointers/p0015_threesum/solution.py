from typing import List


class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        """
        Returns all unique triplets that sum to zero.

        Approach: sort + outer loop + inner two pointers.
        Sort enables two-pointer search and easy duplicate skipping.
        Skip duplicate values at the outer level (i) and inner level
        (left/right) to avoid repeated triplets in the output.

        Time:  O(n^2) — outer loop O(n) * inner two pointers O(n)
        Space: O(1) extra (excluding output)
        """
        nums.sort()
        result = []

        for i in range(len(nums) - 2):
            if nums[i] > 0:
                break                          # all remaining sums will be positive
            if i > 0 and nums[i] == nums[i - 1]:
                continue                       # skip duplicate outer values

            left, right = i + 1, len(nums) - 1
            while left < right:
                s = nums[i] + nums[left] + nums[right]
                if s == 0:
                    result.append([nums[i], nums[left], nums[right]])
                    while left < right and nums[left] == nums[left + 1]:
                        left += 1              # skip duplicate left values
                    while left < right and nums[right] == nums[right - 1]:
                        right -= 1             # skip duplicate right values
                    left += 1
                    right -= 1
                elif s < 0:
                    left += 1
                else:
                    right -= 1

        return result
