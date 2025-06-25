package binarySearch;

public class BinarySearchProblems {

    static int ceilingOfANumber(int[] arr, int target) {
        if (arr == null || (target > arr[arr.length - 1])) {
            return -1;
        } else if (arr.length == 1) {
            return arr[0];
        } else {
            int start = 0;
            int end = arr.length - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (arr[mid] == target) {
                    return arr[mid];
                }
                if (target < arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            return arr[start];
        }
    }

    static int floorOfANumber(int[] arr, int target) {
        if (arr == null || (target < arr[0])) {
            return -1;
        } else if (arr.length == 1) {
            return arr[0];
        } else {
            int start = 0;
            int end = arr.length - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (arr[mid] == target) {
                    return arr[mid];
                }
                if (target < arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            return arr[end];
        }
    }

    public static char nextGreatestLetter(char[] letters, char target) {
        int start = 0;
        int end = letters.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target < letters[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return letters[start % (letters.length)];
    }

    public static int[] searchRange(int[] nums, int target) {
        int []ans = {-1, -1};
        int first = search(nums, target, true);
        if (first == -1){return ans;}
        int last = search(nums, target, false);
        ans[0] = first;
        ans[1] = last;
        return ans;
    }

    private static int search(int[] arr, int target, boolean lookForFirstIndex) {
        int ans = -1;
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target < arr[mid]) {
                end = mid - 1;
            } else if (target > arr[mid]){
                start = mid + 1;
            }else {
                ans = mid;
                if (lookForFirstIndex){
                    end = mid - 1;
                }else {
                    start = mid + 1;
                }
            }
        }
        return ans;
    }

    static int findIndexOfElementInSortedInfiniteArray(int []arr, int target){
        int start = 0;
        int end = 1;

        while (target > arr[end]){
            int newStart = end + 1;
            end = (end - start + 1) * 2;
            start = newStart;
        }
        return findIndexOfElementInSortedInfiniteArrayHelper(arr, target, start, end);
    }

    static int findIndexOfElementInSortedInfiniteArrayHelper(int []arr, int target, int start, int end){
        while (start <= end){
            int mid = start + (end - start) / 2;
            if (target == arr[mid]){
                return mid;
            }
            if (target < arr[mid]){
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static int peakIndexInMountainArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end){
            int mid = start  + (end - start)  / 2 ;
            if ( arr[mid] > arr[mid + 1]){
                end  = mid;
            }else {
                start = mid + 1;
            }
        }
        return start;
    }

    public static void main (String[]args){
        int[] arr = {1,2,1,3,5,6,4};
        System.out.println(peakIndexInMountainArray(arr));
    }
}
