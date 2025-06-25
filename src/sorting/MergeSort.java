package sorting;

import java.util.Arrays;

public class MergeSort {
    static int[] sort(int[] arr){
        if (arr.length == 1){
            return arr;
        }
        int mid = arr.length/2;
        int [] left = sort(Arrays.copyOfRange(arr, 0, mid));
        int [] right = sort(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(left, right);
    }

    private static int[] merge(int []left, int []right){
        int left_pointer = 0;
        int right_pointer = 0;
        int newArrayPointer = 0;
        int []resArr = new int[left.length + right.length];
        while (left_pointer < left.length && right_pointer < right.length){
            if (left[left_pointer] < right[right_pointer]){
                resArr[newArrayPointer] = left[left_pointer];
                left_pointer++;
            }else {
                resArr[newArrayPointer] = right[right_pointer];
                right_pointer++;
            }
            newArrayPointer++;
        }
        while (left_pointer < left.length){
            resArr[newArrayPointer] = left[left_pointer];
            left_pointer++;
            newArrayPointer++;
        }
        while (right_pointer < right.length){
            resArr[newArrayPointer] = right[right_pointer];
            right_pointer++;
            newArrayPointer++;
        }
        return resArr;
    }
    public static void main(String[] args) {
        int []arr= {3,6,2};
        var resArr = sort(arr);
        System.out.println(Arrays.toString(resArr));
    }
}
