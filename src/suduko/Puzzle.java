/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package suduko;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author abhinendra
 */
public class Puzzle {

    int[][] filledBoard
            = {{5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}};

    public static int[][] swapRow(int[][] board) {
        Random randomNum = new Random();
        int num1 = randomNum.nextInt(3);
        int num2 = randomNum.nextInt(3);

        int temp = 0;
        for (int i = 0; i < 9; i++) {
            temp = board[num1][i];
            board[num1][i] = board[num2][i];
            board[num2][i] = temp;
        }

        int num3 = randomNum.nextInt(6 - 3) + 3;
        int num4 = randomNum.nextInt(6 - 3) + 3;

        temp = 0;
        for (int i = 0; i < 9; i++) {
            temp = board[num3][i];
            board[num3][i] = board[num4][i];
            board[num4][i] = temp;
        }

        int num5 = randomNum.nextInt(9 - 6) + 6;
        int num6 = randomNum.nextInt(9 - 6) + 6;

        temp = 0;
        for (int i = 0; i < 9; i++) {
            temp = board[num5][i];
            board[num5][i] = board[num6][i];
            board[num6][i] = temp;
        }

        return board;

    }

    public static int[][] swapColumn(int[][] board) {
        Random randomNum = new Random();
        int num1 = randomNum.nextInt(3);
        int num2 = randomNum.nextInt(3);

        int temp = 0;
        for (int i = 0; i < 9; i++) {
            temp = board[i][num1];
            board[i][num1] = board[i][num2];
            board[i][num2] = temp;
        }

        int num3 = randomNum.nextInt(6 - 3) + 3;
        int num4 = randomNum.nextInt(6 - 3) + 3;

        temp = 0;
        for (int i = 0; i < 9; i++) {
            temp = board[i][num3];
            board[i][num3] = board[i][num4];
            board[i][num4] = temp;
        }

        int num5 = randomNum.nextInt(9 - 6) + 6;
        int num6 = randomNum.nextInt(9 - 6) + 6;

        temp = 0;
        for (int i = 0; i < 9; i++) {
            temp = board[i][num5];
            board[i][num5] = board[i][num6];
            board[i][num6] = temp;
        }

        return board;

    }

    public static int[][] transposeMatrix(int[][] arr) {
        int temp = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = i; j < 9; j++) {
                temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;

            }
        }
        return arr;
    }

    public static int[][] getPuzzle() {

        return transposeMatrix(swapColumn(swapRow(SudokuValidator.filledBoard)));
    }

    public static void main(String[] args) {

        //below code is written for testing of Puzzle.java and SudokuValidator.java classes
//        int [][] arr = swapColumn(swapRow(SudokuValidator.filledBoard));
//        for(int i = 0; i <9; i++){
//            for(int j = 0; j<9; j++){
//                System.out.print(arr[i][j]+" ");
//            }
//            System.out.println();
//        }
//        
//        if(SudokuValidator.isValidSudoku(arr)){
//            System.out.println("true");
//        }else System.out.println("false");
    }
}
