package dynamicProgramming.concepts.oneD_DP;

import java.util.Arrays;

public class Leetcode300 {

    //memoization
//    public static int lengthOfLIS(int[] nums) {
//        int[][] dp = new int[nums.length + 1][nums.length +1];
//        for (int []a : dp){
//            Arrays.fill(a, -1);
//        }
//        return helper(nums, 0,  -1, dp);
//    }
//    private static int helper(int[] nums, int i, int prev, int[][] dp){
//        if (prev != -1 && dp[i][prev] != -1){
//            return dp[i][prev];
//        }
//        if (i >= nums.length){
//            return 0;
//        }
//        int take = 0;
//        if (prev == -1 || nums[prev] < nums[i]){
//            take = 1 + helper(nums, i +1, i, dp);
//        }
//        int skip = helper(nums, i +1, prev, dp);
//        if (prev != -1){
//            dp[i][prev] = Math.max(take, skip);
//        }
//        return Math.max(take, skip);
//    }

    public static int lengthOfLIS(int[] nums) {
        int max = 1;
        int len = nums.length;
        int []dp = new int[len + 5];
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++){
            for (int j = 0; j < i;  j++){
                if (nums[j] < nums [i]){
                    dp[i] = Math.max(dp[i], dp[j]+ 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }
}
