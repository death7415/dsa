package dynamicProgramming.concepts.oneD_DP;

import java.util.Arrays;

public class Leetcode213 {
    public static int rob(int[] nums) {
        int size = nums.length;
        if (size == 1){
            return nums[0];
        }
        if (size == 2){
            return Math.max(nums[0], nums[1]);
        }
        int []dp = new int[size + 5];

        Arrays.fill(dp, -1);
        int max_for_0th_index_and_skip_last_index = solve(nums, 0, size - 2, dp);

        Arrays.fill(dp, -1);
        int max_from_1st_index_and_last_index = solve(nums, 1, size - 1, dp);

        return Math.max(max_for_0th_index_and_skip_last_index, max_from_1st_index_and_last_index);
    }

    private static int solve(int []arr, int i, int size, int []dp){
        if ( i > size){
            return 0;
        }
        if (dp[i] != -1) return dp[i];

        int steal = arr[i] + solve(arr, i +2, size, dp);
        int skip = solve(arr, i +1, size, dp);

        return dp[i] = Math.max(steal, skip);
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{2,3,2}));
    }
}
