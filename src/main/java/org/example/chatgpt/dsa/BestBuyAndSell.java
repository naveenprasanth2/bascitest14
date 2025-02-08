package org.example.chatgpt.dsa;

public class BestBuyAndSell {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        findBestBuyAndSell(prices);
    }

    private static void findBestBuyAndSell(int[] prices) {
        int minValue = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            minValue = Math.min(minValue, price);
            maxProfit = Math.max(maxProfit, (price - minValue));
        }
        System.out.println(maxProfit);
    }


}
