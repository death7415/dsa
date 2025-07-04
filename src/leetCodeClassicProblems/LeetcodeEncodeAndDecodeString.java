package leetCodeClassicProblems;

import java.util.ArrayList;
import java.util.List;

public class LeetcodeEncodeAndDecodeString {

    public static String encode(List<String> strs) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < strs.size()){
            sb.append(strs.get(i).length())
                    .append("#")
                    .append(strs.get(i));
            i++;
        }
        return sb.toString();
    }

    public static List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        int len = str.length();
        StringBuilder numLen = new StringBuilder();
        for (int i = 0; i < len; i++){
            if(str.charAt(i) != '#'){
                 numLen.append(str.charAt(i));
            }else {
                i += 1;
                int strLenToFetch = Integer.parseInt(numLen.toString());
                res.add(str.substring(i, i + strLenToFetch));
                numLen = new StringBuilder();
                i = i + strLenToFetch - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> str = List.of("neet","code","love","you");
        System.out.println(encode(str));

        System.out.println("=====");

        System.out.println(decode(encode(str)));
    }
}
