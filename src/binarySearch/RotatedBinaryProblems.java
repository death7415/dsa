package binarySearch;

public class RotatedBinaryProblems {

    static int findNumberOfTimesSortedArrayIsRotated(int []arr){
        int start = 0 ;
        int end = arr.length - 1;
        int pivot = -1;
        while (start <= end){
            int mid = start + (end - start) / 2;
            if (mid < end && arr[mid] > arr[mid + 1]){
                pivot =  mid;
                break;
            }else if(mid > start  && arr[mid-1] > arr[mid]){
                pivot =  mid -1;
                break;
            }else if (arr[mid] <= arr[start] ){
                end = mid -1;
            }else{
                start = mid + 1;
            }
        }
      return pivot + 1;
    }

    /**
     * Given an array of 1s and 0s which has all 1s first followed by all 0s.
     * Find the number of 0s. Count the number of zeroes in the given array.
     * @return int
     */
    static int countNumberOfZeros(int[] arr){
        int start = 0;
        int end = arr.length -1;
        int indxFirst = 0;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if((mid == 0 || arr[mid - 1] == 1) && arr[mid] == 0){
                indxFirst = mid;
                break;
            }else if (arr [mid] == 1){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return arr.length - indxFirst;
    }

    /*
        410. Split Array Largest Sum
        Given an integer array nums and an integer k, split nums into k non-empty
        sub-arrays such that the largest sum of any subarray is minimized.
        Return the minimized largest sum of the split.
        A subarray is a contiguous part of the array.

        Example 1:

        Input: nums = [7,2,5,10,8], k = 2
        Output: 18
        Explanation: There are four ways to split nums into two sub-arrays.
        The best way is to split it into [7,2,5] and [10,8],
        where the largest sum among the two sub-arrays is only 18.
     */
    public static int splitArray(int[] nums, int k) {
        int start  = 0;
        int end = 0;
        for (int num : nums) {
            start = Math.max(start, num);
            end += num;
        }
         while (start < end){
             int mid = start + (end - start) / 2;

             int sum = 0;
             int counter = 1;
             for (int num : nums){
                 if (sum + num > mid){
                     sum = num;
                     counter ++;
                 }else {
                     sum += num;
                 }
             }
             if (counter > k){
                 start = mid + 1;
             }else {
                 end = mid;
             }
         }
         return end;
    }
    // 7,8,9,1,2,3,4,5,6
    public static int findInRotatedSortedArrayWithUniqueElement(int []arr, int target){
        int start = 0;
        int end = arr.length -1;

        while (start <=  end){
            int mid = start + (end -  start) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[start] <= arr[mid]){
               if (arr[start] <= target && target <= arr[mid]){
                   end = mid -1;
               }else {
                   start = mid +1;
               }
            }else {
                if (arr[mid] <= target && target <= arr[end]){
                    start = mid +1;
                }else {
                    end = mid -1;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] arr = {7,8,9,1,2,3,4,5,6};
        int target = 3;
        System.out.println(findInRotatedSortedArrayWithUniqueElement(arr, target));
    }
}
