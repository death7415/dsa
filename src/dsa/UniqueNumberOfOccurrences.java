package dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class UniqueNumberOfOccurrences {

    public static boolean uniqueOccurrences(int[] arr) {
        var list = new ArrayList<Integer>();
        Arrays.sort(arr);
        Arrays.stream(arr)
                .boxed()
                .distinct()
                .toList()
                .forEach(
                        num -> {
                            System.out.println(num);
                            list.add(count(arr, num));
                        }
                );
        System.out.println(list);
        var resList = list.stream().distinct().toList();
        return list.size() == resList.size();
    }
    private static int count(int []arr, int num){
        int count = 0;
        for (int n : arr){
            if (n == num){
                count ++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int []arr = {-3,0,1,-3,1,1,1,-3,10,0};
        System.out.println(uniqueOccurrences(arr));
    }
}
