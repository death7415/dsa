package integerRecusrion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubsetSumOfAllSubsets {
    static List<Integer> getSubsetSums(int []arr, int length){
        List<Integer> result = new ArrayList<>();
        helper(arr, length, 0, result, 0);
        Collections.sort(result);
        return result;
    }

    private static void helper(int []arr, int length, int i, List<Integer> result, int sum){
        if (i >= length){
            result.add(sum);
            return;
        }
        helper(arr, length, i+1, result, sum + arr[i]);
        helper(arr, length, i+1, result, sum);
    }

    public static void main(String[] args) {
        int[] arr = {3,1,2};
        var res = getSubsetSums(arr, arr.length);
        System.out.println(res);
    }
}
