package leetCodeClassicProblems;

import java.util.*;

public class Leetcode76 {
    public static String minWindow(String s, String t) {
        int len = s.length();
        if(s.length() < t.length()) return "";
        if (s.equals(t)) return s;
        int start = 0;
        String result = s;
        boolean flag = false;
        HashMap<Character,Integer> tFreq_map = new HashMap<>();
        for (char c: t.toCharArray()){
            tFreq_map.put(c , tFreq_map.getOrDefault(c, 0) + 1);
        }
        //System.out.println(tFreq_map);
        HashMap<Character, Integer> s_checkMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++ ){
            char ch = s.charAt(i);
            s_checkMap.put(ch, s_checkMap.getOrDefault(ch, 0) + 1);
            if (tFreq_map.size() <= s_checkMap.size() && checkMapFrequencies(tFreq_map, s_checkMap)){
                if (s.substring(start, i+1).length() <= result.length()){
                    result = s.substring(start, i + 1);
                    flag = true;
                }
                while (start < len){
                    s_checkMap.put(s.charAt(start), s_checkMap.getOrDefault(s.charAt(start),0) - 1);
                    start += 1;
                    if (!checkMapFrequencies(tFreq_map, s_checkMap)){
                        break;
                    }
                    if (s.substring(start, i+1).length() <= result.length()){
                        result = s.substring(start, i+1);
                        flag = true;
                    }
                }
            }
        }
        if (flag){
            return result;
        }
        return "";
    }

    private static boolean checkMapFrequencies(Map<Character, Integer> tFreq_map, Map<Character, Integer> s_checkMap){
        for (var map : tFreq_map.entrySet()){
            if (s_checkMap.get(map.getKey()) == null || map.getValue() > s_checkMap.get(map.getKey())){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(minWindow("a", "b"));
    }
}
