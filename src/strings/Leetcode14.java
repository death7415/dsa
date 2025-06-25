package strings;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Leetcode14 {
//    public static String longestCommonPrefix2(String[] strs) {
////        int i =0;
////        int j = 0;
////        while(){
////            char ch = strs[0].charAt(i);
////            while (j < strs.length){
////                char dusre = strs[j + 1].charAt(i);
////            }
////        }
//    }


    public static String longestCommonPrefix(String[] strs) {
        if (strs == null){
            return "";
        }if (strs.length == 1){
            return strs[0];
        }
        int len = strs.length;
        List<String> list = Arrays.asList(strs);
        Collections.sort(list);
        String first = list.getFirst();
        String last = list.getLast();

        int pointer1= 0;
        int pointer2 =0;

        String res = "";

        while (pointer1 < first.length() && pointer2 < last.length()){
            if (first.charAt(pointer1) == last.charAt(pointer2)){
                res += first.charAt(pointer1);
                pointer1 ++;
                pointer2 ++;
            }else{
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"dog","racecar","car"}));

    }
}
