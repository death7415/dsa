package leetCodeClassicProblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leetcode75 {

    public static void sortColors(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int n : nums) {
            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
        }

        int zeroCount = freqMap.getOrDefault(0, 0);
        int oneCount = freqMap.getOrDefault(1, 0);
        int twoCount = freqMap.getOrDefault(2, 0);
        int i = 0;
        while (i < nums.length && zeroCount >= 0 && oneCount >= 0 && twoCount >= 0) {
            if(zeroCount > 0){
                nums[i++] = 0;
                zeroCount --;
                continue;
            }
            if(oneCount > 0){
                nums[i++] = 1;
                oneCount --;
                continue;
            }
            if(twoCount > 0){
                nums[i++] = 2;
                twoCount --;
            }
        }
    }

    public static void main(String[] args) {
        int []nums = {1, 0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
