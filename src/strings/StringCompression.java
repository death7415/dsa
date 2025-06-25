package strings;

import java.util.Arrays;

public class StringCompression {

    // 'a', 'a', 'a', 'b', 'b', 'b'
    public static int compress(char[] chars) {
        int updateIndex = 0;
        char current_char = chars[0];
        int counter = 0;
        int i = 0;
        for(;i < chars.length; i++){
            if (current_char == chars[i]){
                counter ++;
            }else {
                chars[updateIndex] = current_char;
                updateIndex++;
                if (counter > 1) {
                    var counterString = String.valueOf(counter);
                    var res = counterString.toCharArray();
                    for (char ch : res) {
                        chars[updateIndex] = ch;
                        updateIndex++;
                    }
                }
                current_char = chars[i];
                counter = 1;
            }
        }
        if(i == chars.length){
            chars[updateIndex] = current_char;
            updateIndex++;
            if (counter > 1) {
                var counterString = String.valueOf(counter);
                var res = counterString.toCharArray();
                for (char ch : res) {
                    chars[updateIndex] = ch;
                    updateIndex++;
                }
            }
        }
        System.out.println(Arrays.toString(chars));
        return updateIndex;
    }
    public static void main(String[] args) {
        char[] chars = {'a','b','b','b','b','b','b','b','b','b','b','b','b','c','c','c','c','c'};
        System.out.println(compress(chars));
    }
}
