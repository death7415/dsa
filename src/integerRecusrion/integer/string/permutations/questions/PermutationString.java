package integerRecusrion.integer.string.permutations.questions;

import java.util.ArrayList;
import java.util.List;

public class PermutationString {
    public static List<List<String>> permute(String[] nums) {
        List<List<String>> result = new ArrayList<>();
        boolean []freq = new boolean[nums.length];
        helper(nums, result, new ArrayList<>(), freq);
        return result;
    }
    private static void helper(String []arr, List<List<String>> result, List<String> current, boolean[] freq){
        if (current.size() == arr.length){
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < arr.length; i++){
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
        String s  = "eru";
        String  []str = s.split("");
        System.out.println(permute(str));
    }
}
