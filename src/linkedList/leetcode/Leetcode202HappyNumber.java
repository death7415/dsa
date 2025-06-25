package linkedList.leetcode;

import java.util.HashMap;

public class Leetcode202HappyNumber {
    //studied approach

//    public static boolean isHappy(int n) {
//        int slow = n;
//        int fast = n;
//
//        do {
//            slow = findSquare(slow);
//            fast = findSquare(findSquare(fast));
//        }while (slow != fast);
//        return slow == 1;
//    }
//
//    private static int findSquare(int num){
//        int ans = 0;
//        while (num > 0){
//            int rem = num % 10;
//            ans += rem * rem;
//            num = num / 10;
//        }
//        return ans;
//    }


    // personal approach

    public static boolean isHappy(int n) {
        HashMap<Integer, Integer> map= new HashMap<>();
        int ans = getSquare(n);
        while (true){
            if (map.containsKey(ans)){
                return false;
            }else if (ans != 1 && !map.containsKey(ans)){
                map.put(ans, n);
                ans = getSquare(ans);
            }else {
                return true;
            }
        }
    }

    private static int getSquare(int n){
        int ans =0;
        while (n > 0){
            int rem = n % 10;
            ans += rem *  rem;
            n = n / 10;
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(isHappy(0));
    }
}
