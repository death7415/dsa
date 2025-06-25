package leetCodeClassicProblems;

public class Leetcode42 {

    public static int trap(int[] height) {
        int length = height.length;
        int []prefix = new int[length]; //from left to right calculate max num for each index
        int []suffix = new int[length]; //from right to left calculate max num for each index
        int ans = 0;
        prefix[0] = height[0];
        suffix[length - 1] = height[length -1];

        for(int i= 1; i < length; i++){
            prefix[i] = Math.max(prefix[i-1], height[i]);
        }

       for (int i = length - 2; i >=0; i--){
           suffix[i] = Math.max(suffix[i + 1], height[i]);
       }

       for (int i =0; i < length; i++){ //find min from prefix and suffix arr for same index and subtract current index
           ans += Math.min(prefix[i], suffix[i]) - height[i];
       }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
