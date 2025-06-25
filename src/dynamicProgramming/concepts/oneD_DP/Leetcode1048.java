package dynamicProgramming.concepts.oneD_DP;

import java.util.Arrays;
import java.util.Comparator;

public class Leetcode1048 {
    private static boolean isPredecessor(String a, String b){
        if (b.length() - a.length() != 1){
            return false;
        }
        int i = 0, j = 0;
        while (i < a.length() && j < b.length()){
            if (a.charAt(i) == b.charAt(j)){
                i++;
            }
            j++;
        }
        return i == a.length();
    }

    /// for rec and memoization
//    private static int solve(String[]words, int i , int prev, int[][]dp){
//        if (prev != -1 && dp[i][prev] != -1){
//            return dp[i][prev];
//        }
//        if (i >= words.length){
//            return  0;
//        }
//        int take = 0, skip = 0;
//        if (prev == -1 || isPredecessor(words[prev], words[i])){
//            take = 1 + solve(words, i+1, i, dp);
//        }
//        skip = solve(words, i+1, prev, dp);
//        if (prev != -1){
//            dp[i][prev] = Math.max(skip, take);
//        }
//        return Math.max(take, skip);
//    }
//
//    public static int longestStrChain(String[] words) {
//        int [][]dp = new int[words.length + 5][words.length + 5];
//        for (int []ar : dp){
//            Arrays.fill(ar, -1);
//        }
//        Arrays.sort(words, Comparator.comparingInt(String::length));
//        return solve(words, 0, -1, dp);
//    }

    //for tabularization
    public static int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int maxL = 1;
        int[] dp = new int [words.length + 5];
        Arrays.fill(dp, 1);
        for (int i = 1; i < words.length; i++) {
            for (int j = 0; j < i; j++) {
                if (isPredecessor(words[j], words[i])){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxL = Math.max(maxL, dp[i]);
                }
            }
        }
        return maxL;
    }

    public static void main(String[] args) {
        System.out.println(longestStrChain(new String[]{"abcd","dbqca"}));
    }
}
