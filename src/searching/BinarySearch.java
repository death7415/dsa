package searching;

public class BinarySearch {

    static int binarySearch(int[]arr, int target){
        if (arr == null){
            return -1;
        }
        if (arr[0] == target){
            return 0;
        }else {
            int start = 0;
            int end = arr.length -1;

            while (start <= end){
                int mid = start + (end - start) / 2;
                if (arr[mid] == target){
                    return mid;
                }
                if (target < arr[mid]){
                    end = mid -1;
                }else if (target > arr[mid]){
                    start = mid + 1;
                }else {
                    return mid;
                }
            }
        }
        return -1;
    }

    static int orderAgnosticBS(int []arr, int target){
        if (arr == null){
            return -1;
        }
        if (arr[0] == target){
            return 0;
        }else {
            int start = 0;
            int end = arr.length -1;

            while (start <= end){
                int mid = start + (end - start) / 2;
                if (arr[mid] == target){
                    return mid;
                }
                if (arr[start] <  arr[end]){
                    if (target < arr[mid]){
                        end = mid -1;
                    }else {
                        start = mid + 1;
                    }
                }else {
                    if (target > arr[mid]){
                        end = mid -1;
                    }else {
                        start = mid + 1;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int []arr = {2,4,7,9,12,14,15,19};
        System.out.println(binarySearch(arr, 12));
    }
}
