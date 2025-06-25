package leetCodeClassicProblems;

import java.util.Stack;

public class Leetcode32 {
    public static int longestValidParentheses(String str) {
        String s = " "+str;
        Stack<Integer> stack = new Stack<>();
        int maxLen = 0;
        int i = 0;
        while (i < s.length()){
            char ch = s.charAt(i);
            if (stack.isEmpty() || ch == '('){
                stack.push(i);
            }else {
                stack.pop();
                if (stack.isEmpty()){
                    stack.push(i);
                }else if(ch == ')'){
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
            i++;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "()";
        System.out.println(longestValidParentheses(s));
    }
}
