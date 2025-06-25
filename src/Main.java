import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.*;

public class Main {

    public static long fact(long n){
       if (n == 1){
           return 1;
       }
       return n * fact(n-1);
    }

    public static long sum(long n){
        if (n == 1){
            return 1;
        }
        return n + sum(n-1);
    }

    public static int[]  reverseArray(int[] arr, int i){
        if (i >= arr.length/2){
            return arr;
        }
        swap(arr, i, arr.length-i-1);
        return reverseArray(arr,i+1);
    }

    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static boolean checkPalindrome(String str, int i){
        if(str.charAt(i) != str.charAt(str.length()-i-1)){
            return false;
        }
        if (i >= str.length()/2){
          return true;
        }
        return checkPalindrome(str, i+1);
    }

    public static int fibonacci(int n){
        if (n <= 1){
            return n;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }

    /**
     *
     *
     * TODO: findAllOrderedSequenceSets
     *
     *
     **/
    static ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    public static void findAllOrderedSequenceSets(int index, ArrayList<Integer> resArr, int[] arr){
        if (index >= arr.length){
            result.add(new ArrayList<>(resArr));
            return;
        }
        resArr.add(arr[index]);
        findAllOrderedSequenceSets(index+1, resArr, arr);
        resArr.removeLast();
        findAllOrderedSequenceSets(index+1, resArr, arr);
    }

    public static void findSumOfAllSequences(int index, int[] arr, int sum, ArrayList<Integer> resArr, int desiredSum){
        /*
             TODO: below is the code for finding all sequences for desired-sum
         */
//        if (index >= arr.length){
//            if (sum == desiredSum){
//                result.add(new ArrayList<>(resArr));
//                return;
//            }
//            return;
//        }
//        resArr.add(arr[index]);
//        sum += arr[index];
//        findSumOfAllSequences(index+1, arr, sum, resArr, desiredSum);
//        resArr.removeLast();
//        sum -= arr[index];
//        findSumOfAllSequences(index+1, arr, sum, resArr, desiredSum);


        /*
             TODO: below is the code for finding first sequences for desired-sum and make method return as boolean
         */
//        if (index >= arr.length){
//            if (sum == desiredSum){
//                result.add(new ArrayList<>(resArr));
//                return true;
//            }
//            return false;
//        }
//        resArr.add(arr[index]);
//        sum += arr[index];
//        if(findSumOfAllSequences(index + 1, arr, sum, resArr, desiredSum)){
//            return true;
//        }
//        resArr.removeLast();
//        sum -= arr[index];
//        return findSumOfAllSequences(index + 1, arr, sum, resArr, desiredSum);

         /*
             TODO: below is the code for finding total number of sequences for desired-sum and make method return as int
         */
//        if (index >= arr.length){
//            if (sum == desiredSum){
//                result.add(new ArrayList<>(resArr));
//                return 1;
//            }
//            return 0;
//        }
//        resArr.add(arr[index]);
//        sum += arr[index];
//        int pick = findSumOfAllSequences(index + 1, arr, sum, resArr, desiredSum);
//        resArr.removeLast();
//        sum -= arr[index];
//        int nonPick = findSumOfAllSequences(index + 1, arr, sum, resArr, desiredSum);
//        return pick+nonPick;
    }

    public static int sumOfFirstNN(int n){
        if (n <= 0){
            return 0;
        }
        int sumValue = sumOfFirstNN(n-1);
        return sumValue + n;
    }

    public static String decimalToBinary(int decimal){
        if (decimal == 0){
            return "";
        }
        String res = decimalToBinary(decimal/2);
        return res + (decimal % 2);
    }

    public static int reverseNumber(int num, int res){
        if (num == 0){
            return res;
        }

        res = res * 10 +(num % 10);
        return reverseNumber(num/10, res);
    }

    public static String reverseString(String str, String res, int index){
        if (index < 0){
            return res;
        }
        res = res + str.charAt(index);
        return reverseString(str, res, index-1);
    }

    public static void reverseArray(String []arr, int index){
        if (index == arr.length/2)return;
        reverseArray(arr, index+1);
        swapStringArr(arr, index, arr.length-index-1);
    }

    private static void swapStringArr(String[] arr, int index, int i) {
        String temp = arr[index];
        arr[index] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
    }
}