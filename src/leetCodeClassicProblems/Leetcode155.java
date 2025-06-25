package leetCodeClassicProblems;

import java.util.AbstractMap;
import java.util.Stack;

class MinStack {
    Stack<AbstractMap.SimpleImmutableEntry<Integer, Integer>> s1;

    public MinStack() {
        s1 = new Stack<>();
    }

    public void push(int val) {
        if (s1.isEmpty()){
            s1.push(new AbstractMap.SimpleImmutableEntry<>(val, val));
        }else {
            if (s1.peek().getValue() < val){
                s1.push(new AbstractMap.SimpleImmutableEntry<>(val, s1.peek().getValue()));
            }else{
                s1.push(new AbstractMap.SimpleImmutableEntry<>(val, val));
            }
        }

    }

    public void pop() {
        s1.pop();
    }

    public int top() {
       return s1.peek().getKey();
    }

    public int getMin() {
        return s1.peek().getValue();
    }
}

public class Leetcode155 {


    public static void main(String[] args) {

    }
}
