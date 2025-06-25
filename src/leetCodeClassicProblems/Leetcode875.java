package leetCodeClassicProblems;

public class Leetcode875 {
    public static int minEatingSpeed(int[] piles, int h) {

        //koko ki maa ki chut
        int lower = 1;
        int upper = Integer.MIN_VALUE;
        for (int n : piles){
            upper = Math.max(upper, n);
        }
        while(lower <= upper){
            int mid = lower + (upper - lower)/ 2;
            int i  = 0;
            long time = 0;
            while(i < piles.length){
                time +=(long) Math.ceil((double) piles[i] / mid);
                i++;
            }
            if (time > h){
                lower = mid + 1;
            }else {
                upper = mid - 1;
            }
        }
        return lower;
    }

    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[]{805306368,805306368,805306368}, 1000000000));
    }
}
