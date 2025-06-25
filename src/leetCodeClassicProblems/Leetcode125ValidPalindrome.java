package leetCodeClassicProblems;

public class Leetcode125ValidPalindrome {
    public static boolean isPalindrome(String s) {
        String nonAlphanumericString = s.replaceAll("[^a-zA-Z0-9]","").toLowerCase();
        int start_pointer = 0;
        int end_pointer = nonAlphanumericString.length() - 1;
        while (end_pointer >= start_pointer){
            if (nonAlphanumericString.charAt(start_pointer) != nonAlphanumericString.charAt(end_pointer)){
                return false;
            }else {
                end_pointer -= 1;
                start_pointer += 1;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(".a"));
    }
}
