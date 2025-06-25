package leetCodeClassicProblems;

import java.util.Arrays;
import java.util.HashMap;

public class Leetcode567 {

    public static boolean checkInclusion(String s1, String s2) {
        if(s2.length() < s1.length()) return false;
        if(s1.isEmpty()) return true;

        int toCheck = 0;
        for(char c: s1.toCharArray()){
            toCheck += c;
        }

        int indexPointer = 0;
        int toCheckSum = 0;
        int counter = 0;
        int updatePointer = 1;
        StringBuilder charAdd = new StringBuilder();

        while (indexPointer < s2.length()){
            if ((counter % (s1.length()) == 0) && toCheckSum == toCheck){
                if (stringContains(charAdd.toString(), s1)){
                    return true;
                }else {
                    charAdd = new StringBuilder();
                }
            }
            charAdd.append(s2.charAt(indexPointer));
            toCheckSum += s2.charAt(indexPointer);
            indexPointer += 1;
            counter += 1;
            if (counter >= s1.length()){
                if ((counter % (s1.length()) == 0) && toCheckSum == toCheck){
                    if (stringContains(charAdd.toString(), s1)){
                        return true;
                    }
                }
                counter = 0;
                toCheckSum = 0;
                indexPointer = updatePointer;
                updatePointer += 1;
                charAdd = new StringBuilder();
            }
        }
        return false;
    }

    private static boolean stringContains(String one, String two){
        char []ch1= one.toCharArray();
        char []ch2 = two.toCharArray();
        Arrays.sort(ch1);
        Arrays.sort(ch2);
        String newOne = new String(ch1);
        String newTwo = new String(ch2);
        return newOne.equals(newTwo);
    }

    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo"));
    }
}
