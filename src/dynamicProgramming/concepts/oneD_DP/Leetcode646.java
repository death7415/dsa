package dynamicProgramming.concepts.oneD_DP;

import java.util.Arrays;
import java.util.Comparator;

public class Leetcode646 {
        /// recursion with memo

//    public static int findLongestChain(int[][] pairs) {
//        int [][]dp = new int[pairs.length +  5][pairs.length + 5];
//        for (int []arr: dp) {
//            Arrays.fill(arr, -1);
//        }
//        return helper(pairs, 0, -1, dp);
//    }
//
//    private static int helper(int[][] arr, int i , int prev, int [][]dp){
//        if (prev != -1 && dp[i][prev] != -1 ){
//            return dp[i][prev];
//        }
//        if (i >= arr.length){
//            return 0;
//        }
//        int take = 0;
//        if (prev == -1 || arr[prev][1] < arr[i][0]){
//            take = 1 + helper(arr, i + 1, i, dp);
//        }
//        int skip = helper(arr, i + 1, prev, dp);
//
//        if (prev != -1){
//            dp[i][prev] = Math.max(take, skip);
//        }
//        return Math.max(take, skip);
//    }

    // bottom up
    public static int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(nums -> nums[0]));
        int[]dp = new int[pairs.length +  5];
        Arrays.fill(dp, 1);
        int maxLen = 1;
        for (int i = 1; i < pairs.length; i++){
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxLen = Math.max(dp[i], maxLen);
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int [][]arr = {{1,2},{7,8},{4,5}};
        Arrays.sort(arr, Comparator.comparingInt(nums -> nums[0]));
        System.out.println(findLongestChain(arr));
    }
}
