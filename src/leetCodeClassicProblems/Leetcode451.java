package leetCodeClassicProblems;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Leetcode451 {
    public static String frequencySort(String s) {
        PriorityQueue<Map.Entry<Character, Integer>> maxPq = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        HashMap<Character, Integer> fMap = new HashMap<>();
        for (char ch: s.toCharArray()){
            fMap.put(ch, fMap.getOrDefault(ch, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : fMap.entrySet()) {
            maxPq.offer(entry);
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!maxPq.isEmpty()){
            Map.Entry<Character, Integer> entry = maxPq.poll();
            int freq = entry.getValue();
            char character = entry.getKey();
            while (freq > 0){
                stringBuilder.append(character);
                freq--;
            }
        }
        return stringBuilder.toString();

    }

    public static void main(String[] args) {
        System.out.println(frequencySort("cccaaa"));
    }
}
