package sorting;

import java.util.Arrays;

public class CyclicSort {

    public static int[] sort(int[] arr){
        int i = 0;
        while(i < arr.length){
            if (arr[i] != arr[arr[i] -1]){
                swap(arr, i, arr[i]-1);
            }else {
                i++;
            }
        }
        return arr;
    }

    private static void swap(int[]arr, int index, int correctIndexForElement){
        int temp = arr[index];
        arr[index] = arr[correctIndexForElement];
        arr[correctIndexForElement] = temp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sort(new int[]{5,4,3,2,1})));
    }
}
