package integerRecusrion;

import java.util.ArrayList;
import java.util.List;

public class Leetcode39CombinationSum {
    public static List<List<Integer>> combinationSum(int[] arr, int target) {
        List<List<Integer>> res = new ArrayList<>();
        return getSumCombination(arr, 0, target, res ,new ArrayList<>());
    }

    private static List<List<Integer>> getSumCombination(int[] arr, int i, int target, List<List<Integer>> res, ArrayList<Integer> current) {
        if (i >= arr.length){
            if (target == 0){
                res.add(new ArrayList<>(current));
            }
            return res;
        }
        if (arr[i] <= target){
            current.add(arr[i]);
            getSumCombination(arr, i, target - arr[i], res, current);
            current.removeLast();
        }
        getSumCombination(arr, i+1, target, res, current);
        return res;
    }

    public static void main(String[] args) {
        int []arr = {1, 1, 2, 5};
        int target = 6;
        System.out.println(combinationSum(arr, target));
    }
}
