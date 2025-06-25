package leetCodeClassicProblems;

import java.util.Objects;
import java.util.Stack;

public class Leetcode150 {

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        int pointer = 0;
        while (pointer < tokens.length){
            String ch = tokens[pointer];
            if (Objects.equals(ch, "+") && !stack.isEmpty() && stack.size() >= 2){
                int last = stack.pop();
                int first = stack.pop();
                stack.push(first + last);
            } else if (Objects.equals(ch, "-") && !stack.isEmpty() && stack.size() >= 2) {
                int last = stack.pop();
                int first = stack.pop();
                stack.push(first - last);
            }else if (Objects.equals(ch, "*") && !stack.isEmpty() && stack.size() >= 2){
                int last = stack.pop();
                int first = stack.pop();
                stack.push(first * last);
            } else if (Objects.equals(ch, "/") && !stack.isEmpty() && stack.size() >= 2) {
                int last = stack.pop();
                int first = stack.pop();
                stack.push(first / last);
            }else {
                int num = Integer.parseInt(ch);
                stack.push(num);
            }
            pointer++;
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));
    }
}
