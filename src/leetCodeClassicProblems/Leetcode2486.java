package leetCodeClassicProblems;


/*

Example 1:

Input: s = "coaching", t = "coding"
Output: 4
Explanation: Append the characters "ding" to the end of s so that s = "coachingding".
Now, t is a subsequence of s ("coachingding").
It can be shown that appending any 3 characters to the end of s will never make t a subsequence.

 */
public class Leetcode2486 {
    public static int appendCharacters(String s, String t) {
        int first_string_pointer = 0;
        int second_string_pointer = 0;

        while(first_string_pointer < s.length() && second_string_pointer < t.length()){
            if (s.charAt(first_string_pointer) == t.charAt(second_string_pointer)){
                first_string_pointer += 1;
                second_string_pointer += 1;
            }else {
                first_string_pointer += 1;
            }
        }
        return t.length() - second_string_pointer;
    }

    public static void main(String[] args) {
        System.out.println(appendCharacters("z", "abcde"));
    }
}
