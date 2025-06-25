package searching;

public class LinearSearch {

    static int linearSearch(int[]arr, int item){
        if (arr.length == 0){
            return -1;
        }
        if (arr[0] == item){
            return 0;
        }
        int i = 1;
        while (i < arr.length){
            if (arr[i] == item){
                return i;
            }
            i++;
        }
        return -1;
    }

    static int[][] searchIn2DArray(int[][]arr, int target){
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
               if (arr[i][j] == target){
                   return new int[][]{{i,j}};
               }
            }
        }
        return new int[][]{{-1, -1}};
    }

    public static int findNumbers(int[] nums) {
        int count = 0;
        for (int n: nums){
            if (String.valueOf(n).length() % 2 == 0){
                count ++;
            }
        }
        return count;
    }

    public static int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int[] account : accounts) {
            int sum = 0;
            for (int i : account) {
                sum += i;
            }
            max = Math.max(sum,max);
        }
        return max;
    }

    public static void main(String[] args) {
        int [][]arr = {{2,8,7}, {7,1,3}, {1,9,5}};
        System.out.println(maximumWealth(arr));
//        int[][] arr= {
//                {2,5,4},
//                {6,12,8},
//                {9,13,15},
//                {0,1,10}
//        };
//        System.out.println(Arrays.deepToString(searchIn2DArray(arr, 2)));
    }
}
