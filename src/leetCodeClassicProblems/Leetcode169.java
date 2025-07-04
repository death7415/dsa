package leetCodeClassicProblems;

import java.util.LinkedHashMap;
import java.util.Map;

public class Leetcode169 {
    public int majorityElement(int[] nums) {
        int wantFreq = nums.length / 2;
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() > wantFreq){
                return entry.getKey();
            }
        }
        return 0;
    }
    public static void main(String[] args) {

    }
}
