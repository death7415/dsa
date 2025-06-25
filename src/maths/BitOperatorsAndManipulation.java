package maths;

public class BitOperatorsAndManipulation {
    static boolean checkEvenOrOdd(int num){
        return (num & 1) == 0;
    }

    static int getUniqueElementFromArray(int[]arr){
        int unique = 0;
        for (int n : arr){
            unique = unique ^ n;
        }
        return unique;
    }

    static int findIthBit(int num, int bitToFind){
//        int mask = 1 << bitToFind - 1;
//        return (num & mask) != 0 ? 1 : 0;
        return (num >> bitToFind - 1) & 1;
    }

    public static void main(String[] args) {
        int[]arr = {2,2,3,1,3,6,1};
        System.out.println(findIthBit(7, 1));
    }
}
