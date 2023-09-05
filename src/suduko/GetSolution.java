/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package suduko;

import java.util.Arrays;
import javax.swing.JOptionPane;
import static suduko.SudokuValidator.isNumberInBox;
import static suduko.SudokuValidator.isNumberInColumn;
import static suduko.SudokuValidator.isNumberInRow;

/**
 *
 * @author abhinendra
 */
public class GetSolution {

    static int[][] solution = new int[9][9];

    public static int[][] solveSudoku(int[][] board) {
        if (solveBoard(board)) {
            JOptionPane.showMessageDialog(null, "Unsolvable!!!");
        }
        return board;
    }

    public static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        if (isNumberInRow(board, number, row) > 1 || isNumberInColumn(board, number, column) > 1 || isNumberInBox(board, number, row, column) > 1) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean solveBoard(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (board[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= 9; numberToTry++) {
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;

                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
