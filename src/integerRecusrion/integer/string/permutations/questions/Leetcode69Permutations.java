package integerRecusrion.integer.string.permutations.questions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode69Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean []freq = new boolean[nums.length];
        helper(nums, result, new ArrayList<>(), freq);
        return result;
    }

    private static void helper(int []arr, List<List<Integer>> result, List<Integer> current, boolean[] freq){
        if (current.size() == arr.length){
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i =0; i < arr.length; i++){
            if (!freq[i]){
                freq[i] = true;
                current.add(arr[i]);
                helper(arr, result, current, freq);
                current.removeLast();
                freq[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int []arr = {1,2,3};
        System.out.println(permute(arr));
    }
}
