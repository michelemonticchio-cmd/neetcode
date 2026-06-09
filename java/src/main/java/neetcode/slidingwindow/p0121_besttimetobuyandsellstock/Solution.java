package neetcode.slidingwindow.p0121_besttimetobuyandsellstock;

public class Solution {
    /**
     * Returns the maximum profit obtainable from a single buy-then-sell
     * transaction on the given prices, where the sell day must come strictly
     * after the buy day.
     *
     * Approach: single pass tracking the minimum price seen so far.
     * For each day i, the best profit ending on day i is
     * prices[i] - minPriceSoFar, where minPriceSoFar uses only days < i
     * because we update it after computing the profit.
     *
     * Time:  O(n)
     * Space: O(1)
     */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            // Try selling today at "price", using minPrice from previous days
            int profit = price - minPrice;
            if (profit > maxProfit) {
                maxProfit = profit;
            }
            // Then update the running minimum for future iterations
            if (price < minPrice) {
                minPrice = price;
            }
        }

        return maxProfit;
    }
}
