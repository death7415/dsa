package leetCodeClassicProblems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Leetcode912 {
    public static int[] sortArray(int[] nums) {
        PriorityQueue<Integer> mingPq = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        for (int num: nums) {
            mingPq.offer(num);
        }
        int i = 0;
        while (!mingPq.isEmpty()){
            nums[i++] = mingPq.poll();
        }
        return nums;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortArray(new int[]{5,2,3,1})));
    }
}
