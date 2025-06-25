package leetCodeClassicProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens_One {
    static List<List<String>> result = new ArrayList<>();

    public static List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        solve(board, n, 0);
        return result;
    }

    private static void solve(char[][] board,int n, int row){
        if (row >= n){
            List<String> temp = new ArrayList<>();
            for (char[] r : board) {
                temp.add(new String(r));
            }
            result.add(temp);
            return;
        }

        for (int col = 0; col < n; col++){
            if (isValidRowAndColumn(board, row, col, n)){
                board[row][col] = 'Q';
                solve(board, n, row+1);
                board[row][col] = '.';
            }
        }
    }

    private static boolean isValidRowAndColumn(char[][] board, int row, int col, int n){
        //checking for direct above row, for same column
        int checkForUpwardRow = row;
        while (checkForUpwardRow >= 0){
            if(board[checkForUpwardRow][col] == 'Q'){
                return false;
            }
            checkForUpwardRow -= 1;
        }
        //checking for direct above left diagonal columns
        int checkForDiagonalLeftCol_for_row = row;
        int checkForDiagonalLeftCol_for_col = col;
        while (checkForDiagonalLeftCol_for_row >= 0 && checkForDiagonalLeftCol_for_col >= 0){
            if (board[checkForDiagonalLeftCol_for_row][checkForDiagonalLeftCol_for_col] == 'Q'){
                return false;
            }
            checkForDiagonalLeftCol_for_row -= 1;
            checkForDiagonalLeftCol_for_col -= 1;
        }
        //checking for direct above right diagonal columns
        int checkForDiagonalRightCol_for_row = row;
        int checkForDiagonalRightCol_for_col = col;
        while (checkForDiagonalRightCol_for_row >= 0 && checkForDiagonalRightCol_for_col < n){
            if (board[checkForDiagonalRightCol_for_row][checkForDiagonalRightCol_for_col] == 'Q'){
                return false;
            }
            checkForDiagonalRightCol_for_row -= 1;
            checkForDiagonalRightCol_for_col += 1;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solveNQueens(1));
    }
}
