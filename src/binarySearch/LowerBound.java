package binarySearch;

public class LowerBound {
    static  int findLowerBound(int []arr, int element){
        int start = 0;
        int end = arr.length -1;
        int ans = arr.length;

        while (start <=  end){
            int mid = start + (end - start)/2;
            if(arr[mid] >= element){
                ans = mid;
                end = mid -1;
            }else {
                start = mid +1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
