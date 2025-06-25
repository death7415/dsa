package leetCodeClassicProblems;

import java.util.*;

public class Leetcode239 {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return new int[0];
        if (nums.length == 1 ) return nums;
        List<Integer> res = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0;  i < nums.length; i++){
            while (!deque.isEmpty() && deque.peekFirst() <= i-k){
                deque.removeFirst();
            }
            while (!deque.isEmpty() && nums[i] >= nums[deque.getLast()]){
                deque.removeLast();
            }
            deque.add(i);
            if (i >= k-1){
                res.add(nums[deque.getFirst()]);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
    }
}
