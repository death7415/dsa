package stack.leetcode;

import java.util.Stack;

class MyQueue {
    Stack<Integer> forInsertOps;
    public MyQueue() {
        forInsertOps = new Stack<>();
    }

    public void push(int x) {
        forInsertOps.push(x);
    }

    public int pop() {
        return forInsertOps.removeFirst();
    }

    public int peek() {
        return forInsertOps.firstElement();
    }

    public boolean empty() {
        return forInsertOps.isEmpty();
    }
}
public class Leetcode232 {
}
