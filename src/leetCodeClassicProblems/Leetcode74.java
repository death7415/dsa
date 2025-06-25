package leetCodeClassicProblems;

import java.util.Arrays;
import java.util.Collections;

public class Leetcode74 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int[] arr = Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .toArray();

        int start = 0;
        int end = arr.length - 1;
        while (start <= end){
             int mid = start + (end  - start) / 2;
             if (arr[mid] == target){
                 return true;
             }else if (arr[mid] < target){
                 start = mid +1;
             }else {
                 end = mid - 1;
             }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
