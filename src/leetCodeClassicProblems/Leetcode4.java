package leetCodeClassicProblems;

import java.util.Arrays;

public class Leetcode4 {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int []merged = new int[n + m];
        int k = 0;
        for (int ele : nums1) {
            merged[k] = ele;
            k++;
        }

        for (int ele : nums2) {
            merged[k] = ele;
            k++;
        }

        Arrays.sort(merged);

        int len = merged.length;
        if (len % 2 == 0){
            int middle1 = merged[len / 2 - 1];
            int middle2 = merged[len/2];

            return (double) (middle1 + middle2) / (double) 2;
        }else {
            return (double) merged[len / 2];
        }
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
    }
}
