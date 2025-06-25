package integerRecusrion;

import java.util.ArrayList;
import java.util.List;

public class SubsetSum {
    static List<List<Integer>> getSets(int []arr, int i, int sum, int target, List<List<Integer>> res, ArrayList<Integer> current){
        if(i >= arr.length){
            if (sum == target) {
                res.add(new ArrayList<>(current));
            }
            return res;
        }
        current.add(arr[i]);
        sum += arr[i];
        getSets(arr, i+1, sum, target, res, current);
        current.removeLast();
        sum -= arr[i];
        getSets(arr, i+1, sum, target, res, current);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        List<List<Integer>> res = new ArrayList<>();
        List<List<Integer>> allSubsets = getSets(arr, 0, 0, 2,  res ,new ArrayList<>());
        for (List<Integer> subset : allSubsets) {
            System.out.println(subset);
        }
    }
}
