package dynamicProgramming.concepts.oneD_DP;

import java.util.Arrays;

public class Leetcode213BottomUp {
    public static int rob(int[] nums) {
        int n = nums.length;
        int[] profitDp_till_i_house = new int[n + 5];
        profitDp_till_i_house[0] = 0;

        //picking first house and skipping last house, as asked in question
        for (int i =1; i <= n-1 ;i++){
            int steal = nums[i-1] + ((i-2 >= 0) ? profitDp_till_i_house[i-2] : 0);
            int skip = profitDp_till_i_house[i-1];

            profitDp_till_i_house[i] = Math.max(steal, skip);
        }
        int result1= profitDp_till_i_house[n-1];

        Arrays.fill(profitDp_till_i_house, 0);

        profitDp_till_i_house[0] = 0;
        profitDp_till_i_house[1] = 0; //we are adding profit for 1st house as zero,
        // because we will pick last house but skip first, as given condition in first;

        for (int i = 2; i <= n ; i++) {
            int steal = nums[i-1] + ((i-2 >= 0) ? profitDp_till_i_house[i-2] : 0);
            int skip = profitDp_till_i_house[i-1];

            profitDp_till_i_house[i] = Math.max(steal, skip);
        }

        int result2 = profitDp_till_i_house[n];

        return Math.max(result1, result2);

    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{2,3,2}));
    }
}
