package leetCodeClassicProblems;

import java.util.*;

public class Leetcode502 {
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<AbstractMap.SimpleEntry<Integer, Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt(AbstractMap.SimpleEntry::getValue));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < capital.length; i++) {
            minHeap.offer(new AbstractMap.SimpleEntry<>(profits[i], capital[i]));
        }

        while (k > 0){
            while (!minHeap.isEmpty() && minHeap.peek().getValue() <= w){
                maxHeap.offer(minHeap.poll().getKey());
            }
            if (maxHeap.isEmpty()){
                break;
            }
            w += maxHeap.poll();
            k--;
        }
        return w;
    }

    public static void main(String[] args) {

    }
}
