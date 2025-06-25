package dynamicProgramming.concepts.oneD_DP;

import java.util.Arrays;

public class Fibonacci {

    private static int bottomUpApproach(int n){
        int[] dp = new int[n + 2];
        dp[0] = 0;
        dp[1] = 1;

        for (int i =2 ; i <= n ; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    private static int solve(int n, int[] dp){
        if(n <= 1){
            return n;
        }
        if (dp[n] != -1){
            return dp[n];
        }
        return dp[n] = solve(n-1, dp) + solve(n-2, dp);
    }

    private static int fib(int n) {
        int []dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(n, dp);
    }

    private static int normalFib(int n){
        if (n <= 1) {
            return n;
        }
        return normalFib(n-1) + normalFib( n- 2);
    }

    public static void main(String[] args) {
        System.out.println(bottomUpApproach(500000));
    }
}
