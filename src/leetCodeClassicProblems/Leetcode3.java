package leetCodeClassicProblems;

import java.util.HashMap;

public class Leetcode3 {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;
        HashMap<Character, Integer> freqMap = new HashMap<>();
        int start = 0;
        int len = 1;
        int ans = 0;
        freqMap.put(s.charAt(0), 0);

        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!freqMap.containsKey(ch)){
                freqMap.put(ch, i);
            }else {
                if (freqMap.get(ch) >= start ) {
                    start = freqMap.get(ch) + 1;
                }
                freqMap.put(ch, i);
            }
            len = i - start + 1;
            ans = Math.max(len, ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
