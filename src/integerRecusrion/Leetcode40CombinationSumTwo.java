package integerRecusrion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode40CombinationSumTwo {

    public static List<List<Integer>> combinationSum2(int[] arr, int target) {
        Arrays.sort(arr);
        List<List<Integer>> res = new ArrayList<>();
        getCombinationSumSets(arr, 0,  target, res, new ArrayList<>());
        return res;
    }

    private static void getCombinationSumSets(int []arr, int ind, int target, List<List<Integer>> res, List<Integer> current){
        System.out.println("================================");
        System.out.println(" ind = " + ind);
        System.out.println(" target = " + target);
        System.out.println(" current list has = " + current);
        if (target == 0){
            res.add(new ArrayList<>(current));
        }
        for (int i = ind; i < arr.length; i++) {
            System.out.println(" ind = " + ind);
            System.out.println(" i = " + i);
            System.out.println(" target = " + target);
            System.out.println(" current list has = " + current);
            System.out.println(" arr[i] = " + arr[i]);
            if(i != 0){
                System.out.println( "arr[i-1] = " + arr[i-1]);
            }
            if (i > ind && arr[i]== arr[i-1]) continue;
            if (arr[i] > target) return;
            current.add(arr[i]);
            getCombinationSumSets(arr, i + 1, target-arr[i], res, current);
            current.removeLast();
        }
    }

    public static void main(String[] args) {
        int []arr= {1, 1, 2};
        System.out.println(combinationSum2(arr, 3));
    }
}
