package strings;

public class Leetcode38 {
    public static String countAndSay(int n) {
        if (n <= 1){
            return "1";
        }
        String say = countAndSay(n-1);
        return process(say);
    }

    private static String process(String say) {
        int i =0, counter = 0 ;
        char current_char = say.charAt(0);
        StringBuilder res = new StringBuilder();

        while (i < say.length()){
            if (current_char == (say.charAt(i))){
                counter ++;
            }else {
                res.append(counter).append(current_char);
                current_char = say.charAt(i);
                counter = 1;
            }
            i++;
        }
        res.append(counter).append(current_char);
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(7));
    }
}
