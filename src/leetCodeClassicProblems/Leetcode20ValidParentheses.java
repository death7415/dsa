package leetCodeClassicProblems;

import java.util.Stack;

public class Leetcode20ValidParentheses {
    public static boolean isValid(String s) {
        if(s == null || s.isEmpty()){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < s.length()){
         if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '['){
             stack.push(s.charAt(i));
         } else {
             char c = s.charAt(i);
             if (stack.isEmpty() ||
                     c == ')' && stack.pop() != '(' ||
                     c == '}' && stack.pop() != '{' ||
                     c == ']' && stack.pop() != '['){
                 return false;
             }
         }
         i++;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("]"));
    }
}
