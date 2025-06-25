package recursion;

import java.util.ArrayList;
import java.util.Arrays;

public class RecursionByKunalSorting {

    static boolean checkArrayIsSorted(int []arr, int index){
        if (index == arr.length-1) return true;
        return  (arr[index] < arr[index + 1] && checkArrayIsSorted(arr, index + 1));
    }

    static int linearSearch(int[]arr, int target, int index){
        if (index == arr.length) return -1;
        if(arr[index] == target) return index;
        return linearSearch(arr, target, index + 1);
    }

    static boolean linearSearchWithBoolean(int[]arr, int target, int index){
        if (index == arr.length) return false;
        if(arr[index] == target) return true;
        return linearSearchWithBoolean(arr, target, index + 1);
    }

    static ArrayList<Integer> linearSearchAllIndex(int[]arr, int target, int index, ArrayList<Integer> list){
        if (index == arr.length) return list;
        if(arr[index] == target) list.add(index);
        return linearSearchAllIndex(arr, target, index + 1, list);
    }

    static ArrayList<Integer> linearSearchWithListInsideBody(int[]arr, int target, int index){
        var list = new ArrayList<Integer>();
        if (index == arr.length) return list;
        if(arr[index] == target) list.add(index);
        var listFromRecentFunctionCall = linearSearchWithListInsideBody(arr, target, index + 1);
        list.addAll(listFromRecentFunctionCall);
        return list;
    }
    // rotated binary search

    static int rotatedBinarySearch(int[]arr, int start, int end, int target){
        if (start > end) return -1;
        int mid = start + (end - start)/2;
        if (target == arr[mid]) return mid;
        if (arr[start] <= arr[mid]){
            if (target >= arr[start] && target <= arr[mid]){
                return rotatedBinarySearch(arr, start, mid -1, target);
            }else {
                return rotatedBinarySearch(arr, mid + 1, end, target);
            }
        }else {
            if (target >= arr[mid] && target <= arr[end]){
                return rotatedBinarySearch(arr, mid + 1, end, target);
            }else {
                return rotatedBinarySearch(arr, start, mid - 1, target);
            }
        }
    }

    static void bubbleSort(int[]arr, int row, int col){
        if (row == 0){
            return;
        }
        if (col < row){
            if (arr[col] > arr[col+1]){
                int temp = arr[col];
                arr[col] = arr[col + 1];
                arr[col +1] = temp;
            }
            bubbleSort(arr, row, col + 1);
        }else {
            bubbleSort(arr, row - 1, 0);
        }
    }

    static void selectionSort(int[]arr, int row, int col, int maxIndex){
        if (row == 0) return;
        if (col < row){
            if (arr[col] > arr[maxIndex]) {
                selectionSort(arr, row, col +1 , col);
            }
            else {
                selectionSort(arr, row, col +1, maxIndex);
            }
        }else {
            int temp = arr[maxIndex];
            arr[maxIndex] = arr[row-1];
            arr[row-1] = temp;
            selectionSort(arr, row -1, 0 , 0);
        }
    }

    static int[] mergeSort(int[]arr){
        if (arr.length == 1){
            return arr;
        }
        int mid = arr.length/2;
        int[]left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int[]right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));

        return mergeArrays(left, right);
    }

    private static int[] mergeArrays(int[] left, int[] right) {
        int[]resArr = new int[left.length + right.length];
        int leftArrPointer = 0;
        int rightArrPointer = 0;
        int resArrPointer = 0;

        while (leftArrPointer < left.length && rightArrPointer < right.length){
            if (left[leftArrPointer] < right[rightArrPointer]){
                resArr[resArrPointer] = left[leftArrPointer];
                leftArrPointer++;
            }else {
                resArr[resArrPointer] = right[rightArrPointer];
                rightArrPointer++;
            }
            resArrPointer++;
        }

        while (leftArrPointer < left.length){
            resArr[resArrPointer] = left[leftArrPointer];
            leftArrPointer++;
            resArrPointer++;
        }

        while (rightArrPointer < right.length){
            resArr[resArrPointer] = right[rightArrPointer];
            rightArrPointer++;
            resArrPointer++;
        }
        return resArr;
    }

//    static void inPlaceMergeSort(int[]arr, int start, int end){
//
//    }




    public static void main(String[] args) {
//        int[]arr= {1,4,2,3};
//        selectionSort(arr, arr.length,  0, 0);
//        System.out.println(Arrays.toString(arr));

        int[]arr = {3,6,2,1,8,9,10,13,5};
        System.out.println(Arrays.toString(mergeSort(arr)));
    }
}
