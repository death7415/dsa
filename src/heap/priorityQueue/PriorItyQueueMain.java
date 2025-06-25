package heap.priorityQueue;

import java.util.Collections;
import java.util.PriorityQueue;

public class PriorItyQueueMain {

    public static void main(String[] args) {
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
        maxPq.offer(20);
        maxPq.offer(4);
        maxPq.offer(20);
        maxPq.offer(12);
        maxPq.offer(6);
        maxPq.offer(8);

        System.out.println(maxPq.peek());

        PriorityQueue<Integer> minPq = new PriorityQueue<>(); //default always min pq
        minPq.offer(20);
        minPq.offer(4);
        minPq.offer(20);
        minPq.offer(12);
        minPq.offer(6);
        minPq.offer(8);

        System.out.println(minPq.peek());
    }
}
