package binarySearch;

import java.util.Map;

public class Leetcode162 {
    public static int findPeakElement(int[] arr) {
        int start =0;
        int end = arr.length -1;
        while (start < end){
            int mid = start + (end - start) /2;
            if (arr[mid] < arr[mid +1]){
                start = mid +1;
            }else {
                end = mid;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1,2,3,4,5,6,7,2,1}));
    }
}
