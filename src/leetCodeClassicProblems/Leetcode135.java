package leetCodeClassicProblems;

import java.util.Arrays;
import java.util.HashMap;

public class Leetcode135 {

    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);  // Every child gets at least one candy

        // Left to right
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Right to left
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        // Sum up all candies
        int total = 0;
        for (int c : candies) total += c;
        return total;
    }

    public static void main(String[] args) {
        System.out.println(candy(new int []{1,3,2,2,1}));



//        [100, 80, 70, 60, 70, 80, 90, 100, 90, 80, 70, 60, 60]
//


//[6, 7, 6, 5, 4, 3, 2, 1, 0, 0, 0, 1, 0]
//[20000, 20000, 16001, 16001, 16002, 16002, 16003, 16003, 16004, 16004, 16005, 16005, 16006, 16006, 16007, 16007, 16008, 16008, 16009, 16009, 16010, 16010, 16011, 16011, 16012, 16012, 16013, 16013, 16014, 16014, 16015, 16015, 16016, 16016, 16017, 16017, 16018, 16018, 16019, 16019, 16020, 16020, 16021, 16021, 16022, 16022, 16023, 16023, 16024, 16024]
//
//
    }
}
