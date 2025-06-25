package leetCodeClassicProblems;

import java.util.Stack;

//below problem contains multiple different problems, so lucky problem

public class Leetcode85 {
    public static int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int[] arr = new int[col];
        for (int i = 0; i < col; i ++){
            arr[i] = matrix[0][i] == '1' ? 1 : 0;
        }

        int maxArea = findMaxArea(arr);

        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '0'){
                    arr[j] = 0;
                }else {
                    arr[j] += 1;
                }
            }
            maxArea = Math.max(maxArea, findMaxArea(arr));
        }
        return maxArea;
    }

    private static int findMaxArea(int []arr){
        int maxArea = 0;
        int[] width = new int[arr.length];
        int[] nsr = getNextSmallerToRight(arr);
        int[] nsl = getNextSmallerToLeft(arr);

        for (int i = 0; i < arr.length; i++) {
            width[i] = nsr[i] - nsl[i] - 1;
        }
        for (int i = 0; i < arr.length; i++) {
            maxArea = Math.max(maxArea, width[i] * arr[i]);
        }
        return maxArea;
    }

    private static int[] getNextSmallerToRight(int []arr){
        int len = arr.length;
        int []nsr = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for (int i = len - 1; i >= 0; i--){
            if (st.isEmpty()){
                nsr[i] = len; // out of bound index is n as checking for right
            }else {
                while (!st.isEmpty() && arr[st.peek()] >= arr[i]){
                    st.pop();
                }
                if (st.isEmpty()){
                    nsr[i] = len;
                }else {
                    nsr[i]= st.peek();
                }
            }
            st.push(i);
        }
        return nsr;
    }

    private static int[] getNextSmallerToLeft(int []arr){
        int len = arr.length;
        int [] nsl = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < len; i++){
            if (st.isEmpty()){
                nsl[i] = -1; // out of bound index
            }else {
                while (!st.isEmpty() && arr[st.peek()] >= arr[i]){
                    st.pop();
                }
                if (st.isEmpty()){
                    nsl[i] = -1;
                }else {
                    nsl[i]= st.peek();
                }
            }
            st.push(i);
        }
        return nsl;
    }

    public static void main(String[] args) {
        char[][] arr = new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(maximalRectangle(arr));
    }
}
