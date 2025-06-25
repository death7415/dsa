import java.util.Arrays;

public class Solution{
    public static int[][] pattern(int n){
        int [][]res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
               res[i][j] = n - Math.min(i,j);
            }
        }

        return res;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(pattern(5)));
    }
}
