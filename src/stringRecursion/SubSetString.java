package stringRecursion;

import java.util.ArrayList;
import java.util.List;

public class SubSetString {
    static List<String> printSubsets(String resStr, String orgStr, List<String> res){
        if (orgStr.isEmpty()){
            res.add(resStr);
            return res;
        }

        char ch = orgStr.charAt(0);
        printSubsets(resStr + ch, orgStr.substring(1), res);
        printSubsets(resStr, orgStr.substring(1), res);
        return res;
    }

    public static void main(String[] args) {
        var res = printSubsets("", "abc", new ArrayList<>());
        System.out.println(res);
    }
}
