package dynamicProgramming.concepts.oneD_DP;

public class Leetcode198 {

    //getting TLE
//    public static int rob(int[] nums) {
//        return solve(nums, 0);
//    }
//
//    private static int solve(int[] arr, int i){
//        if (i >= arr.length){
//            return 0;
//        }
//        int steal = arr[i] + solve(arr, i + 2);
//        int skip = solve(arr, i+ 1);
//
//        return Math.max(steal, skip);
//    }


//    // with memoization DP
//    public static int rob(int[] nums) {
//        int[] dp = new int[nums.length + 5];
//        Arrays.fill(dp, -1);
//        return solve(nums, 0, dp);
//    }
//
//    private static int solve(int[] arr, int i, int []dp){
//        if (i >= arr.length){
//            return 0;
//        }
//        if (dp[i] != -1){
//            return dp[i];
//        }
//        int steal = arr[i] + solve(arr, i + 2, dp);
//        int skip = solve(arr, i+ 1, dp);
//
//        return dp[i] = Math.max(steal, skip);
//    }

//    // with tabularization
//    public static int rob(int[] nums) {
//        int n = nums.length;
//        int []dp = new int[n + 5];
//        Arrays.fill(dp, 0);
//        dp[0]= 0;
//        dp[1] = nums[0];
//
//        for (int i = 2 ; i<= n; i++){
//            int steal = nums[i-1] + dp[i - 2];
//            int skip = dp[i-1];
//
//            dp[i] = Math.max(steal, skip);
//        }
//
//        return dp[n];
//    }

    public static int rob(int[] nums){
        int n = nums.length;
        int prevPrev= 0;
        int prev = nums[0];

        for (int i = 2 ; i<= n; i++){
            int steal = nums[i-1] + prevPrev;
            int skip = prev;

            int temp = Math.max(steal, skip);

            prevPrev = prev;
            prev = temp;
        }

        return prev;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{2,1}));
    }
}
