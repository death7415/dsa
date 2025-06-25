package integerRecusrion;

import java.util.ArrayList;
import java.util.List;

public class SubsetOfListOfNumbers {
    static List<List<Integer>> subsets(int i, int []arr, List<List<Integer>> res, List<Integer> current){
        if (i == arr.length){
            //System.out.println(current);
            res.add(new ArrayList<>(current));
            return res;
        }
        current.add(arr[i]);
        subsets(i+1, arr, res, current);
        current.removeLast();
        subsets(i+1, arr, res, current);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {};
        List<List<Integer>> res = new ArrayList<>();
        List<List<Integer>> allSubsets = subsets(0, arr, res ,new ArrayList<>());
        for (List<Integer> subset : allSubsets) {
            System.out.println(subset);
        }
    }
}
