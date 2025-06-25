package recursion;


public class RecursionByKunal {
    static void printNumbDescending(int num){
        if (num == 0){
            return ;
        }
        System.out.println(num);
        printNumbDescending(num-1);
    }

    static void printNumAscending(int num){
        if (num == 0){
            return ;
        }
        System.out.println(num);
        printNumAscending(num - 1);
        System.out.println(num);
    }

    static int fact(int num){
        if (num <= 1){
            return 1;
        }
        return num * fact(num -1);
    }

    static int sumOfDigits(int num){
        if (num <= 0){
            return 0;
        }

        return (num%10) + sumOfDigits(num/ 10);
    }

    static int productOfDigits(int num){
        if (num%10 == num){
            return num;
        }

        return (num%10) * productOfDigits(num/ 10);
    }

    static int sum = 0;
    static void reverseNum(int num){
        if (num == 0){
            return;
        }

        int rem = num % 10;
        sum = sum * 10 + rem;
        reverseNum(num/10);
    }

    static boolean isPalindrome(int num){
        helperPalindrome(num);
        return num == sum;
    }

    private static void helperPalindrome(int num) {
        if (num == 0){
            return;
        }
        int rem = num % 10;
        sum = sum * 10 + rem;
        helperPalindrome(num/10);
    }


    static int countZeros(int num, int count){
        if (num == 0){
            return count;
        }
        int rem = num % 10;
        if (rem == 0){
             ++count;
        }
        return countZeros(num/10, count);
    }

    static int countSteps(int num, int steps){
        if (num == 0 ){
            return steps;
        }
        if (num % 2 == 0){
            return countSteps(num/2, steps + 1);
        }
        return countSteps(num-1, steps + 1);
    }

    public static void main(String[] args) {
        System.out.println(countSteps(41, 0));

        //int[] arr = {1,6,8,10,14,17,34};
//       int target = 10;
//       System.out.println(search(arr, target, 0, arr.length - 1));
    }

    static int search(int[] arr, int target, int startIndex, int endIndex){
        if (startIndex > endIndex){
            return -1;
        }
        int mid = startIndex + (endIndex - startIndex) / 2 ;
        if (target == arr[mid]){
            return mid;
        }
        if (target < arr[mid]){
            return search(arr, target, startIndex, mid - 1);
        }
        return search(arr, target, mid + 1, endIndex);
    }
}

