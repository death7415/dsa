package leetCodeClassicProblems;

/*
Given a string s consisting of words and spaces, return the length of the last word in the string.

A word is a maximal substring consisting of non-space characters only.



Example 1:

Input: s = "Hello World"
Output: 5
Explanation: The last word is "World" with length 5.
Example 2:

Input: s = "   fly me   to   the moon  "
Output: 4
Explanation: The last word is "moon" with length 4.
 */
public class Leetcode58 {
    public  static int lengthOfLastWord(String s) {
        String[] res =  s.strip().split(" ");
        int len = res.length;
        System.out.println(res[len-1].length());
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("World"));
    }
}
