package leetCodeClassicProblems;

import java.util.Stack;

public class Leetcode84 {
    private static int[] getNextSmallestToRight(int []arr){
        int len = arr.length;
        int []nsr = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = len - 1; i >= 0 ; i--) {
            if (stack.isEmpty()){
                nsr[i] = len;
            }else {
                while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                    stack.pop();
                }
                if (stack.isEmpty()){
                    nsr[i] = len;
                }else {
                    nsr[i] = stack.peek();
                }
            }
            stack.push(i);
        }
        return nsr;
    }

    private static int[] getNextSmallestToLeft(int []arr){
        int len = arr.length;
        int [] nsl = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len ; i++) {
            if (stack.isEmpty()){
                nsl[i] = -1;
            }else {
                while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                    stack.pop();
                }
                if (stack.isEmpty()){
                    nsl[i] = -1;
                }else {
                    nsl[i] = stack.peek();
                }
            }
            stack.push(i);
        }
        return nsl;
    }

    public static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int maxArea = 0;

        int []nsr = getNextSmallestToRight(heights);
        int []nsl = getNextSmallestToLeft(heights);
        int []width = new int[len];

        for (int i = 0; i < len; i++) {
            width[i] = nsr[i] - nsl[i] - 1;
        }

        for (int i = 0; i < len; i++ ){
            maxArea = Math.max(maxArea, width[i] * heights[i]);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2,4}));
    }
}
