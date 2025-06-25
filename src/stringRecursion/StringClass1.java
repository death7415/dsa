package stringRecursion;

public class StringClass1 {

    public static String removeA(String str, String res, int i){
        if (i > str.length()-1){
            return res;
        }
        if (str.charAt(i) != 'a'){
            res += str.charAt(i);
        }
        return removeA(str, res, i+1);
    }

    public static void main(String[] args) {
        System.out.println(removeA("bbcbcabbcbba", "", 0));
    }
}
