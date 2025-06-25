package leetCodeClassicProblems;

import java.util.HashSet;
import java.util.Set;

public class Leetcode128 {

    public static int longestConsecutive(int[] nums) {
        int start;
        int count = 1;
        int ans = 0;
        Set<Integer> set = new HashSet<>();
        for (int i : nums){
            set.add(i);
        }
        for (int num : set) {
            count = 1;
            if (!set.contains(num - 1)) {
                start = num;
                while (set.contains(++start)) {
                    count++;
                }
            }
            ans = Math.max(count, ans);
        }

        //System.out.println(start);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }
}
