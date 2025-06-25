package binarySearch;

public class MinimumInRotatedSortedArray {
    static int searchMinimum(int []arr){
        int start = 0 ;
        int end = arr.length -1;
        int ans = Integer.MAX_VALUE;

        while (start <= end) {
            int mid = start + (end - start) /2;
            if (arr[start] <= arr[mid]){
                ans = Math.min(ans, arr[start]);
                start = mid +1;
            }
            else {
                ans = Math.min(ans, arr[mid]);
                end = mid -1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[]arr = {11,13,15,17};
        System.out.println(searchMinimum(arr));
    }
}
