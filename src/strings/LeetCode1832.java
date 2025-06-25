package strings;

import java.util.HashSet;
import java.util.Set;

public class LeetCode1832 {
    public static boolean checkIfPangram(String sentence) {
        if (sentence.length() < 26){
            return false;
        }else {
            Set<Character> set = new HashSet<>();
            char[] stringChar = sentence.toCharArray();
            for(char ch: stringChar){
                set.add(ch);
            }
            return  set.size() >=26;
        }
    }
    public static void main(String[] args) {
        System.out.println(checkIfPangram("leetcode"));
    }
}
