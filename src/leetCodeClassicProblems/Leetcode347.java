package leetCodeClassicProblems;

import java.util.*;

class NumberClass implements Comparable<NumberClass>{
    public int element;
    public int freq;
    NumberClass(int element, int freq){
        this.element = element;
        this.freq = freq;
    }


    @Override
    public int compareTo(NumberClass that) {
        return  that.freq - this.freq;
    }
}

public class Leetcode347 {
    public static int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        PriorityQueue<NumberClass> maxPq = new PriorityQueue<NumberClass>();
        HashMap<Integer, Integer> fMap = new HashMap<>();
        for (int i : nums) {
            fMap.put(i, fMap.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : fMap.entrySet()) {
            maxPq.offer(new NumberClass(entry.getKey(), entry.getValue()));
        }

        int i = 0;
        while (i < k){
            NumberClass num = maxPq.poll();
            assert num != null;
            res[i] = num.element;
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{3,0,1,0}, 1)));
    }
}