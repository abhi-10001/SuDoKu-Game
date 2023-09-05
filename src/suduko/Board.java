/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package suduko;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author abhinendra
 */
public class Board extends JFrame implements ActionListener, FocusListener {

    JButton one, two, three, four, five, six, seven, eight, nine, erase, check, newGame, getSolution;
    JTextField currentBlock;

    JTextField[][] blockNo = new JTextField[9][9];
    int[][] puzzle;

    Board() {
        super("SuDoKu");

        setLayout(null);

        int x = 0, y = 0;
        for (int i = 0; i < 9; i++) {
            if (i == 0) {
                y = 0;
            } else if (i == 1) {
                y = 55;
            } else if (i == 2) {
                y = 110;
            } else if (i == 3) {
                y = 170;
            } else if (i == 4) {
                y = 225;
            } else if (i == 5) {
                y = 280;
            } else if (i == 6) {
                y = 340;
            } else if (i == 7) {
                y = 395;
            } else if (i == 8) {
                y = 450;
            }
            for (int j = 0; j < 9; j++) {
                if (j == 0) {
                    x = 0;
                } else if (j == 1) {
                    x = 55;
                } else if (j == 2) {
                    x = 110;
                } else if (j == 3) {
                    x = 170;
                } else if (j == 4) {
                    x = 225;
                } else if (j == 5) {
                    x = 280;
                } else if (j == 6) {
                    x = 340;
                } else if (j == 7) {
                    x = 395;
                } else if (j == 8) {
                    x = 450;
                }

                blockNo[i][j] = new JTextField();
                blockNo[i][j].setBounds(x, y, 55, 55);
                blockNo[i][j].setFont(new Font("Tahoma", Font.PLAIN, 25));
                blockNo[i][j].setHorizontalAlignment(JTextField.CENTER);
                blockNo[i][j].setBackground(Color.WHITE);
                blockNo[i][j].addFocusListener(this);
                add(blockNo[i][j]);
            }

        }

        JPanel jp = new JPanel();
        jp.setBounds(50, 50, 510, 510);
        jp.setBackground(Color.black);
        jp.setLayout(null);
        add(jp);

        JPanel jp2 = new JPanel();
        jp2.setBounds(45, 45, 515, 515);
        jp2.setBackground(Color.black);
        jp2.setLayout(null);
        add(jp2);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                jp.add(blockNo[i][j]);
            }
        }

        one = new JButton("1");
        one.setBounds(600, 250, 70, 70);
        one.setFont(new Font("Tahoma", Font.PLAIN, 30));
        one.setFocusable(false);
        one.addActionListener(this);
        add(one);

        two = new JButton("2");
        two.setBounds(680, 250, 70, 70);
        two.setFont(new Font("Tahoma", Font.PLAIN, 30));
        two.setFocusable(false);
        two.addActionListener(this);
        add(two);

        three = new JButton("3");
        three.setBounds(760, 250, 70, 70);
        three.setFont(new Font("Tahoma", Font.PLAIN, 30));
        three.setFocusable(false);
        three.addActionListener(this);
        add(three);

        four = new JButton("4");
        four.setBounds(600, 330, 70, 70);
        four.setFont(new Font("Tahoma", Font.PLAIN, 30));
        four.setFocusable(false);
        four.addActionListener(this);
        add(four);

        five = new JButton("5");
        five.setBounds(680, 330, 70, 70);
        five.setFont(new Font("Tahoma", Font.PLAIN, 30));
        five.setFocusable(false);
        five.addActionListener(this);
        add(five);

        six = new JButton("6");
        six.setBounds(760, 330, 70, 70);
        six.setFont(new Font("Tahoma", Font.PLAIN, 30));
        six.setFocusable(false);
        six.addActionListener(this);
        add(six);

        seven = new JButton("7");
        seven.setBounds(600, 410, 70, 70);
        seven.setFont(new Font("Tahoma", Font.PLAIN, 30));
        seven.setFocusable(false);
        seven.addActionListener(this);
        add(seven);

        eight = new JButton("8");
        eight.setBounds(680, 410, 70, 70);
        eight.setFont(new Font("Tahoma", Font.PLAIN, 30));
        eight.setFocusable(false);
        eight.addActionListener(this);
        add(eight);

        nine = new JButton("9");
        nine.setBounds(760, 410, 70, 70);
        nine.setFont(new Font("Tahoma", Font.PLAIN, 30));
        nine.setFocusable(false);
        nine.addActionListener(this);
        add(nine);

        erase = new JButton("Erase");
        erase.setBounds(600, 100, 110, 50);
        erase.setFont(new Font("Tahoma", Font.PLAIN, 22));
        erase.setFocusable(false);
        erase.addActionListener(this);
        add(erase);

        check = new JButton("Check");
        check.setBounds(750, 100, 110, 50);
        check.setFont(new Font("Tahoma", Font.PLAIN, 22));
        check.setFocusable(false);
        check.addActionListener(this);
        add(check);

        newGame = new JButton("New Game");
        newGame.setBounds(650, 30, 150, 50);
        newGame.setFont(new Font("Tahoma", Font.PLAIN, 22));
        newGame.setFocusable(false);
        newGame.addActionListener(this);
        add(newGame);

        getSolution = new JButton("Get Solution");
        getSolution.setBounds(650, 170, 180, 50);
        getSolution.setFont(new Font("Tahoma", Font.PLAIN, 22));
        getSolution.setFocusable(false);
        getSolution.addActionListener(this);
        add(getSolution);

        setSize(900, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == one) {
            currentBlock.setText(one.getText());
        } else if (ae.getSource() == two) {
            currentBlock.setText(two.getText());
        } else if (ae.getSource() == three) {
            currentBlock.setText(three.getText());
        } else if (ae.getSource() == four) {
            currentBlock.setText(four.getText());
        } else if (ae.getSource() == five) {
            currentBlock.setText(five.getText());
        } else if (ae.getSource() == six) {
            currentBlock.setText(six.getText());
        } else if (ae.getSource() == seven) {
            currentBlock.setText(seven.getText());
        } else if (ae.getSource() == eight) {
            currentBlock.setText(eight.getText());
        } else if (ae.getSource() == nine) {
            currentBlock.setText(nine.getText());
        } else if (ae.getSource() == erase) {
            currentBlock.setText("");
        } else if (ae.getSource() == check) {
            if (checkSolution(puzzle)) {
                JOptionPane.showMessageDialog(null, "Valid");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid");
            }
            printSudoku(puzzle);
        } else if (ae.getSource() == newGame) {
            puzzle = Puzzle.getPuzzle();
            fillBox(puzzle);
        } else if (ae.getSource() == getSolution) {
            fillSolution(puzzle);
            printSudoku(puzzle);
        }
    }

    public void printSudoku(int[][] puzzle) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void fillSolution(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!blockNo[i][j].getBackground().equals(new Color(173, 216, 230))) {
                    blockNo[i][j].setText(board[i][j] + "");
                }
            }
        }
    }

    public JTextField randomBox() {
        Random rand = new Random();
        int i = rand.nextInt(9);
        int j = rand.nextInt(9);

        return blockNo[i][j];
    }

    public void fillBox(int[][] puzzle) {

        int count = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                blockNo[i][j].setBackground(Color.white);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                blockNo[i][j].setText(puzzle[i][j] + "");
                if (count < 44) {
                    JTextField box = new JTextField();
                    box = randomBox();
                    box.setBackground(new Color(173, 216, 230));
                    box.setDisabledTextColor(Color.black);
                    box.setEnabled(false);
                    box.setEditable(false);
                    count++;
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!blockNo[i][j].getBackground().equals(new Color(173, 216, 230))) {
                    blockNo[i][j].setText("");
                    blockNo[i][j].setFont(new Font("Tahoma", Font.PLAIN, 25));
                    blockNo[i][j].setForeground(new Color(20, 180, 5));
                    blockNo[i][j].setEditable(true);
                    blockNo[i][j].setEnabled(true);
                }
            }
        }
        printSudoku(puzzle);
        System.out.println(SudokuValidator.isValidSudoku(puzzle));
    }

    public static void main(String[] args) {
        new Board();
    }

    public boolean checkSolution(int[][] sol) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (blockNo[i][j].getText().equals("")) {
                    return false;
                }
                sol[i][j] = Integer.parseInt(blockNo[i][j].getText());
            }
        }

        if (SudokuValidator.isValidSudoku(puzzle)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void focusGained(FocusEvent fe) {
        currentBlock = (JTextField) fe.getSource();
    }

    @Override
    public void focusLost(FocusEvent fe) {
        currentBlock = null;
    }
}
