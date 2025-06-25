package dynamicProgramming.concepts.oneD_DP;

import java.util.Arrays;

public class Leetcode1911 {

    ///with below code getting Time limit exceed

//    public static long maxAlternatingSum(int[] nums){
//        long []dp = new long[nums.length + 1];
//        Arrays.fill(dp, -1);
//        return solve(nums, 0, new ArrayList<>(), 0, dp);
//    }
//
//    private static long solve(int []arr, int i, List<Integer> current, int max, long[] dp){
//        if (i >= arr.length){
//            int sumEven = 0;
//            int sumOdd = 0;
//            for (int idx = 0; idx < current.size(); idx++) {
//                if(idx % 2 == 0){
//                    sumEven += current.get(idx);
//                }else {
//                     sumOdd += current.get(idx);
//                }
//            }
//            max = Math.max(max, sumEven - sumOdd);
//            return max;
//        }
//        current.add(arr[i]);
//        long max1 = solve(arr, i + 1, current, max, dp);
//        current.removeLast();
//        long max2 = solve(arr, i + 1, current, max, dp);
//        return Math.max(max1, max2);
//    }

    // below code is for memoization

//    public static long maxAlternatingSum(int[] nums){
//        long[][] dp = new long[nums.length + 5][2];
//        for (long[] row : dp) {
//            Arrays.fill(row, -1);
//        }
//        return solve(nums, 0, true, 0, dp);
//    }
//
//    private static long solve(int []nums, int i, boolean isEvenIndex, long ans, long[][] dp){
//        int parity = isEvenIndex ? 1 : 0; // to check even or odd, if even than 1 if odder than 0
//        if (dp[i][parity] != -1) return dp[i][parity];
//        if (i >= nums.length){
//            return  0;
//        }
//        long take;
//        long notTake;
//        if (isEvenIndex){
//            take = nums[i] + solve(nums, i + 1, false, ans, dp);
//            notTake = solve(nums, i + 1, true, ans, dp);
//        }else {
//            take = solve(nums, i + 1, true, ans, dp) - nums[i];
//            notTake = solve(nums, i + 1, false, ans, dp);
//        }
//        dp[i][parity] = ans = Math.max(take, notTake);
//        return ans;
//    }

    //ye bsd wala greedy pattern


//    public static long maxAlternatingSum(int[] nums) {
//        long res = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            res += Math.max(nums[i] - nums[i - 1], 0);
//        }
//        return res;
//    }

    // tabular dp approach
    public static long maxAlternatingSum(int[] nums){
        if (nums.length == 1){
            return nums[0];
        }
        long [][]dp = new long[nums.length + 5][2];
        for (int i = 1; i < nums.length + 1; i++){
            dp[i][0] = Math.max(dp[i-1][1] - nums[i-1], dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][0] + nums[i-1], dp[i-1][1]);
        }
        return Math.max(dp[nums.length][0], dp[nums.length][1]);
    }



    public static void main(String[] args) {
        System.out.println(maxAlternatingSum(new int[]{4,2,5,3}));
    }
}
