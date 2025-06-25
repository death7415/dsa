package leetCodeClassicProblems;

import java.util.Arrays;

public class Leetcode167 {
    public static int[] twoSum(int[] numbers, int target) {
        int pointer1 = 0;
        int pointer2 = numbers.length - 1;

        while (pointer1 < pointer2){
            int res = numbers[pointer1] + numbers[pointer2];
            if (res == target){
                return new int[]{pointer1 + 1, pointer2 + 1};
            }else if (res> target){
                pointer2 --;
            } else {
                pointer1++;
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{-5,-3,0,2,4,6,8}, 5)));
    }
}
