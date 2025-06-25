package stack.leetcode;

import java.util.*;

public class QueueByKunal {
    static Deque<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) {
        queue.add(2);
        queue.add(4);
        queue.add(1);
        queue.add(6);
        queue.add(12);
        queue.add(7);
        queue.add(2);

        System.out.println(queue.reversed());
        System.out.println(queue);
        //System.out.println(queue.removeAll(List.of(3)));

        //System.out.println(queue);

        //queue.removeFirstOccurrence(2);
        queue.removeLastOccurrence(2);
        System.out.println(queue);
    }
}
