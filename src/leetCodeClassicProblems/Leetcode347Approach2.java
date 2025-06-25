package leetCodeClassicProblems;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Leetcode347Approach2 {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> fMap = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> minPq = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (int i: nums ){
            fMap.put(i, fMap.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry: fMap.entrySet()) {
            minPq.offer(entry);
            if (minPq.size() > k){
                minPq.poll();
            }
        }

        int []res= new int[k];
        int i = 0;
        while (i < k && !minPq.isEmpty()){
            res[i] = minPq.poll().getKey();
            i++;
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
