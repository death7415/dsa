package leetCodeClassicProblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode916 {

    public static List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> result = new ArrayList<>();
        Map<Character, Integer> babyMap = allExistsFromWord2(words2);
        for (String string : words1) {
            HashMap<Character, Integer> papaMap = new HashMap<>();
            boolean flag = false;
            for (char c : string.toCharArray()) {
                papaMap.put(c, papaMap.getOrDefault(c, 0) + 1);
            }
            for(Map.Entry<Character, Integer> set :  babyMap.entrySet()){
                if (papaMap.containsKey(set.getKey()) && (papaMap.get(set.getKey()) >= set.getValue())){
                    flag = true;
                }else {
                    flag = false;
                    break;
                }
            }
            if (flag){
                result.add(string);
            }
        }
        return result;
    }

    private static Map<Character, Integer> allExistsFromWord2(String[] words2) {
        HashMap<Character, Integer> babyMap = new HashMap<>();
        for (int i = 0; i< words2.length; i++){
            Map<Character, Integer> freqMap = new HashMap<>();
            for (char c : words2[i].toCharArray()){
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }
            for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
                char c = entry.getKey();
                int freq = entry.getValue();
                babyMap.put(c, Math.max(babyMap.getOrDefault(c, 0), freq));
            }
        }
        return babyMap;
    }

    public static void main(String[] args) {
        System.out.println(wordSubsets(new String[]{"amazon","apple","facebook","google","leetcode"},
                new String[]{"lo","eo"}));
    }
}
