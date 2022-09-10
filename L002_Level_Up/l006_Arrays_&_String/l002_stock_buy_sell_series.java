public class l002_stock_buy_sell_series {
    // =========================================================================================================================================
    // Question_1 : 121. Best Time to Buy and Sell Stock
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    public int maxProfit(int[] prices) {
        int ov_profit = -(int)1e9; // overall profit
        int min_price = (int)1e9; // minimum price
        
        for(int d = 0; d < prices.length; d++) {
            int p = prices[d]; // price at dth day
            
            // minimum price from 0th day to dth day
            if(p < min_price) 
                min_price = p;  
            int curr_profit = p - min_price; // current profit
            
            ov_profit = Math.max(ov_profit, curr_profit); // maximise the overall profit which is our final result
        }
        
        return ov_profit;
    }

    // =========================================================================================================================================
    // Question_2 : Stock buy and sell 2 - Infinite transection allowed
    public int maxProfit2(int[] prices) {
        int bd = 0; // buying day
        int sd = 0; // selling day
        int ov_profit = 0; // overall profit after transection

        for(int d = 1; d < prices.length; d++) {
            if(prices[d] > prices[d - 1]) 
                sd++;
            else {
                ov_profit += prices[sd] - prices[bd];
                bd = sd = d;
            }
        }
        ov_profit += prices[sd] - prices[bd];
        return ov_profit;

    }

    // =========================================================================================================================================
    // Question_3 : 714. Best Time to Buy and Sell Stock with Transaction Fee
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
    public int maxProfit3(int[] prices, int fee) {
        int pibt = -prices[0]; // profit if buy today
        int pist = 0; // profit if sell today
        
        for(int d = 1; d < prices.length; d++) {
            int curr_pibt = Math.max(pibt, pist - prices[d]); // current profit if buy today
            int curr_pist = Math.max(pist, prices[d] + pibt - fee); // current profit if sell today
            
            pibt = curr_pibt;
            pist = curr_pist;
        }
        
        return Math.max(pibt, pist);
    }

    // =========================================================================================================================================
    // Question_4 : 309. Best Time to Buy and Sell Stock with Cooldown
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
    public int maxProfit4(int[] prices) {
        int pibt = -prices[0]; // profit if buy today
        int pist = 0; // profit if sell today
        int picd = 0; // profit including cooldown
        
        for(int d = 1; d < prices.length; d++) {
            int curr_pibt = Math.max(pibt, picd - prices[d]); // current profit if buy today
            int curr_pist = Math.max(pist, prices[d] + pibt); // current profit if sell today
            picd = pist; // update profit including cooldown
            
            // update pibt and pist
            pibt = curr_pibt;
            pist = curr_pist;
        }
        
        return pist;
    }

    // =========================================================================================================================================
    // Question_5 : 123. Best Time to Buy and Sell Stock III - two transection alllowed
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
     public int maxProfit(int[] prices) {
        int n = prices.length;
        
        int[] dp1 = new int[n]; // max profit if we sell stock today
        int[] dp2 = new int[n]; // max profit if we buy stock today and sell it at max price day in right
        
        int min_price = prices[0]; // minimum price stock
        int max_price = prices[n - 1]; // maximum price stock
        
        int ov_profit = 0; // overall profit 
        
        // tavel left to right and find max profit on each day if we sell stock on that day, so we should buy that stock on minimum price day
        for(int d = 1; d < n; d++) {
            int price = prices[d]; // price on dth day
            min_price = Math.min(min_price, price);
            dp1[d] = Math.max(dp1[d - 1], price - min_price);
        }
        
        // travel right to left and find max profit on each day if we buy stock on that day and sell it at max price day on right
        for(int d = n - 2; d >= 0; d--) {
            int price = prices[d]; // price on dth day
            max_price = Math.max(max_price, price);
            dp2[d] = Math.max(dp2[d + 1], max_price - price);
        }
        
        // find overall max
        for(int d = 0; d < n; d++) {
            int tp1 = dp1[d]; // first transection profit
            int tp2 = dp2[d]; // second transection profit
            
            ov_profit = Math.max(ov_profit, tp1 + tp2);
        }
        
        return ov_profit;
    }
    
    public static void main(string[] args) {
        System.out.println("hello world");
    }

}