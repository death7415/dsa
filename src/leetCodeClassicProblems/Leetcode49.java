package leetCodeClassicProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Leetcode49 {

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String >> res = new HashMap<>();
        for (String str : strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedString =  new String(chars);

            if (!res.containsKey(sortedString)){
                List<String> newlist = new ArrayList<>(List.of(str));
                res.put(sortedString, newlist);
            }else {
                var resL = res.get(sortedString);
                resL.add(str);
                res.put(sortedString, resL);
            }
        }

        return new ArrayList<>(res.values());
    }

    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{ "eat","tea","tan","ate","nat","bat"}));
    }
}
