package linkedList.leetcode;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Leetcode136 {

    public static int singleNumber(int[] nums) {
        long res = 0;
        var out = IntStream.of(nums).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet();

        for (var map: out){
            if (map.getValue() == 1){
                res = map.getKey();
                break;
            }
        }
        return (int)res;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{4,1,2,1,2}));
    }
}
