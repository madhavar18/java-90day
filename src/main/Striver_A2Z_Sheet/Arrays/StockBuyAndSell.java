package Striver_A2Z_Sheet.Arrays;

import java.util.Arrays;

public class StockBuyAndSell {

    public static int maxProfit(int[] prices) {
        int max = 0, min = prices[0];

        for(int i = 1; i < prices.length; i++) {
            int profit = prices[i] - min;
            max = Math.max(max, profit);
            min = Math.min(prices[i], min);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] prices = new int[] {7,1,5,3,6,4};

        System.out.println("The prices on 6 days are: " + Arrays.toString(prices));
        System.out.println("The max profit we can get is: " + maxProfit(prices));
    }
}
