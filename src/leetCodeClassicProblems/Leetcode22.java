package leetCodeClassicProblems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Leetcode22 {
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(n, "(",  res);
        return res;
    }

    private static boolean isValid(String s){
        if(s == null || s.isEmpty()){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < s.length()){
            if (s.charAt(i) == '('){
                stack.push(s.charAt(i));
            } else {
                char c = s.charAt(i);
                if (stack.isEmpty() || c == ')' && stack.pop() != '('){
                    return false;
                }
            }
            i++;
        }
        return stack.isEmpty();
    }

    private static void helper(int n, String ch, List<String> res){
        if (Math.multiplyExact(2, n) == ch.length()){
            if (isValid(ch)){
                res.add(ch);
                ch = "";
            }
            return;
        }
        helper(n, ch + ")",  res);
        helper(n, ch + "(",  res);
    }
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}
