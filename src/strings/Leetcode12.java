package strings;

public class Leetcode12 {
    public static String intToRoman(int num) {
        int []arr = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String []str = {"M", "CM", "D", "CD", "C", "XC", "L","XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < arr.length; i++){
            if (num == 0){
                break;
            }
            int times = num/arr[i];

            while (times > 0){
                res.append(str[i]);
                times--;
            }
            num = num % arr[i];
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(1994));
    }
}
