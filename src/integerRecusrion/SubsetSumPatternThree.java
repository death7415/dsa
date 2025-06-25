package integerRecusrion;

import java.util.ArrayList;
import java.util.List;

public class SubsetSumPatternThree {
    static int getSets(int []arr, int i, int sum, int target, List<List<Integer>> res, ArrayList<Integer> current, int count){
        if (i >= arr.length){
            if (sum ==  target){
                res.add(new ArrayList<>(current));
                return 1;
            }
            return 0;
        }

        current.add(arr[i]);
        sum += arr[i];
        int first = getSets(arr, i +1, sum, target, res, current, count);
        current.removeLast();
        sum-= arr[i];
        int sec = getSets(arr, i +1, sum, target, res, current, count);
        return first + sec;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        List<List<Integer>> res = new ArrayList<>();
        System.out.println("count = " + getSets(arr, 0, 0, 2,  res ,new ArrayList<>(), 0));
//        for (List<Integer> subset : res) {
//            System.out.println(subset);
//        }
    }
}
