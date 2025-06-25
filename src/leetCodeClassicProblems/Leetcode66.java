package leetCodeClassicProblems;

import java.util.Arrays;

public class Leetcode66 {
    public static int[] plusOne(int[] digits) {
        if (digits[digits.length -1] < 9){
            digits[digits.length - 1 ] = digits[digits.length - 1 ] + 1;
            return digits;
        }
        int carry = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9){
                digits[i] = 0;
                carry = 1;
            } else {
                digits[i] = digits[i] + 1;
                carry = 0;
                break;
            }
        }
        if (carry == 1){
            int []res = new int[digits.length + 1];
            res[0] = 1;
            for (int i =1; i < res.length ; i++){
                res[i] = 0;
            }
            return res;
        }
        return digits;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,0,0,6})));
    }
}
