package leetCodeClassicProblems;

public class Leetcode2678 {
    public static int countSeniors(String[] details) {
        int count = 0;
        if (details != null) {
            for (String s : details) {
                if(Integer.parseInt(s.split("[a-zA-Z]")[1].substring(0, 2)) > 60){
                    count++;
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        System.out.println(countSeniors(new String[]{"2921522980M5644"}));
    }
}
