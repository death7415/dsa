package leetCodeClassicProblems;

import java.util.Arrays;
import java.util.Stack;

public class Leetcode739 {
    public static int[] dailyTemperatures(int[] temperatures) {
        int []res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        int i  = 0;
        while (i < temperatures.length){
            if (stack.isEmpty()){
                stack.push(i);
            }else{
                //int toCheckIndex = stack.peek();
                while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                    int toAddAtIndexWhichIsSameAsToRemovedIndex = stack.pop();
                    res[toAddAtIndexWhichIsSameAsToRemovedIndex] = i - toAddAtIndexWhichIsSameAsToRemovedIndex;
                }
                stack.push(i);
            }
            i++;
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));
    }
}
