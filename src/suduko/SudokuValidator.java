/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package suduko;

/**
 *
 * @author abhinendra
 */
public class SudokuValidator {

    static int[][] filledBoard
            = {{5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}};

//    SudokuValidator(){
//        this.filledBoard = filledBoard;
//    }
    public static boolean isValidSudoku(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0 || !isValidPlacement(board, board[i][j], i, j)) {
                    return false;
                }
            }

        }

        return true;
    }

    public static int isNumberInRow(int[][] board, int number, int row) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == number) {
                count++;
            }
        }
        return count;
    }

    public static int isNumberInColumn(int[][] board, int number, int column) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (board[i][column] == number) {
                count++;
            }
        }
        return count;
    }

    public static int isNumberInBox(int[][] board, int number, int row, int column) {
        int count = 0;
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        if (isNumberInRow(board, number, row) > 1 || isNumberInColumn(board, number, column) > 1 || isNumberInBox(board, number, row, column) > 1) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(isValidSudoku(filledBoard));
    }
}
