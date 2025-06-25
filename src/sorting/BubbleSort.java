package sorting;

import java.util.Arrays;

public class BubbleSort {

    public static void sort(int[] arr){
        if (arr.length == 1){
            return;
        }
        for (int i = 0; i < arr.length; i++)
         for (int j= 1; j < arr.length; j++){
             if (arr[j-1] > arr[j]){
                 swap(arr, j-1, j);
             }
         }
    }
    private static void swap(int []arr, int index1, int index2){
        int temp = arr[index2];
        arr[index2] = arr[index1];
        arr[index1] = temp;
    }

    public static void main(String[] args) {
        int []arr = {42, 7, 13, 29, 1, 56, 3, 88, 34, 2, 77, 19};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
