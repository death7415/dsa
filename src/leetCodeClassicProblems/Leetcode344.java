package leetCodeClassicProblems;

import java.util.Arrays;

public class Leetcode344 {
    public static void reverseString(char[] s) {
        int first = 0;
        int last = s.length - 1;

        while (first <= last){
           char temp = s[first];
           s[first] = s[last];
           s[last] = temp;
            first++;
            last--;
        }

    }

    public static void main(String[] args) {
        char []s = {'H','a','n','n','a','h'};
        //reverseString(s);
        System.out.println(Arrays.toString(s));
    }
}
