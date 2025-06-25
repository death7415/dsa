package leetCodeClassicProblems;

public class Leetcode121BuyAndSellStock {

    public static int maxProfit(int[] prices) {
        int min_price = prices[0];
        int maxProfit = 0;

        for (int price: prices
             ) {
            int profit = price - min_price;
            maxProfit = Math.max(maxProfit, profit);
            min_price = Math.min(min_price, price);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7,6,4,3,1}));
    }
}
