package leetCodeClassicProblems;

import java.util.*;

public class Leetcode15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>();
        solve(nums, 0, 0, new ArrayList<>(), res);
        return new ArrayList<>(res);
    }

    private static void solve(int[] nums, int i, int sum, List<Integer> current, Set<List<Integer>> result) {
        if (i >= nums.length) {
            if (sum == 0 && current.size() == 3) {
                List<Integer> temp = new ArrayList<>(current);
                Collections.sort(temp); // to handle uniqueness if needed
                result.add(new ArrayList<>(temp));
            }
            return;
        }
        current.add(nums[i]);
        sum += nums[i];
        solve(nums, i + 1, sum, current, result);
        current.removeLast();
        sum -= nums[i];
        solve(nums, i +1, sum ,  current, result);
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{34,55,79,28,46,33,2,48,31,-3,84,71,52,-3,
                93,15,21,-43,57,-6,86,56,94,74,83,-14,28,-66,46,-49,62,
                -11,43,65,77,12,47,61,26,1,13,29,55,-82,76,26,15,
                -29,36,-29,10,-70,69,17,49}));
    }
}
