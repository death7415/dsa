package integerRecusrion;

import java.util.ArrayList;
import java.util.List;

public class SubsetSumPatternTwo {

    static boolean getSets(int []arr, int i, int sum, int target, List<List<Integer>> res, ArrayList<Integer> current){
        if (i >=  arr.length){
            if (sum == target){
                res.add(new ArrayList<>(current));
                return true;
            }
            return false;
        }
        current.add(arr[i]);
        sum += arr[i];
        if (getSets(arr, i+1, sum, target, res, current)) {
            return true;
        }
        current.removeLast();
        sum -= arr[i];
        if( getSets(arr, i + 1, sum, target, res, current)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        List<List<Integer>> res = new ArrayList<>();
        getSets(arr, 0, 0, 2,  res ,new ArrayList<>());
        for (List<Integer> subset : res) {
            System.out.println(subset);
        }
    }
}
