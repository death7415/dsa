package binarySearch;

public class Leetcode69 {
    //with binary search
    //below ans is not working in leetcode
    public static int mySqrt(int x) {
        int start = 1, ans = 1;
        int end = x;

        while (start <= end){
            int mid = start + (end -  start) / 2;
            if (mid * mid <= x){
                ans = mid;
                start = mid +1;
            }else {
                end = mid -1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(mySqrt(4));
    }
}
