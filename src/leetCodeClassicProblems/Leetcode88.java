package leetCodeClassicProblems;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Leetcode88 {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j =0, k =0;
        Deque<Integer> deque = new ArrayDeque<>();
        while (i < (m+n) && j < n){
            if (nums1[i] <= nums2[j] && nums1[i] != 0){
                i++;
            } else if (nums1[i] > nums2[j] && nums1[i] != 0) {
                deque.offer(nums1[i]);
                nums1[i] = nums2[j];
                i++;
                j++;
            }
            if(j == n){
                while (!deque.isEmpty() && i < (m+n)){
                    nums1[i] = deque.pollFirst();
                    i++;
                }
            }
            if (!deque.isEmpty() && deque.peekFirst() <= nums2[j]){
                nums1[i] = deque.pollFirst();
                i++;
            }

        }

    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int []nums2 = {2,5,6};

        merge(nums1, 3, nums2, 3);

        System.out.println(Arrays.toString(nums1));
    }
}
