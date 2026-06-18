from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        """
        Returns the maximum profit from a single buy-then-sell transaction.

        Approach: single pass tracking the minimum price seen so far.
        For each price, either update the minimum (new buy candidate) or
        compute the profit if selling today and update the maximum.

        Time:  O(n)
        Space: O(1)
        """
        minPrice = float('inf')
        maxProfit = 0

        for price in prices:
            if price < minPrice:
                minPrice = price
            else:
                maxProfit = max(maxProfit, price - minPrice)

        return maxProfit
