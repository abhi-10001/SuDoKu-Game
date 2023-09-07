/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package suduko;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author abhinendra
 */
public class Board extends JFrame implements ActionListener, FocusListener {

    Choice difficulty;
    JButton one, two, three, four, five, six, seven, eight, nine, erase, check, newGame, getSolution, menu, starttime, stoptime;
    JTextField currentBlock;
    int difficultyCount;
    boolean[][] flag = new boolean[9][9]; // to identify every textfield 

    JTextField[][] blockNo = new JTextField[9][9];
    int[][] puzzle;
    int[][] entry = new int[9][9];
    int mistakeCount = 0;

    JLabel mistakecountlbl, timeJLabel;

    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            elapsedTime += 1000;
            hours = (elapsedTime / 3600000);
            minutes = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;

            String seconds_string = String.format("%02d", seconds);
            String minutes_string = String.format("%02d", minutes);
            String hours_string = String.format("%02d", hours);

            timeJLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        }
    });

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

        JLabel difficultylabel = new JLabel("Choose Difficulty Level:");
        difficultylabel.setBounds(65, 30, 255, 30);
        difficultylabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
        add(difficultylabel);

        difficulty = new Choice();
        difficulty.setBounds(340, 30, 120, 30);
        difficulty.setFont(new Font("Railway", Font.PLAIN, 23));
        difficulty.setBackground(new Color(212, 235, 242));
        difficulty.add("Easy");
        difficulty.add("Medium");
        difficulty.add("Hard");
        add(difficulty);

        JLabel timerlbl = new JLabel("Timer: ");
        timerlbl.setBounds(55, 80, 130, 30);
        timerlbl.setFont(new Font("Tahoma", Font.PLAIN, 23));
        add(timerlbl);

        timeJLabel = new JLabel("");
        timeJLabel.setBounds(130, 80, 130, 30);
        timeJLabel.setFont(new Font("Railway", Font.PLAIN, 23));
        add(timeJLabel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/start.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        starttime = new JButton(i3);
        starttime.setBorder(null);
        starttime.setContentAreaFilled(false);
        starttime.setBounds(240, 80, 30, 30);
        starttime.addActionListener(this);
        starttime.setFocusable(false);
        starttime.setVisible(false);
        add(starttime);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/pause.png"));
        Image i5 = i4.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        stoptime = new JButton(i6);
        stoptime.setBorder(null);
        stoptime.setContentAreaFilled(false);
        stoptime.setBounds(240, 80, 30, 30);
        stoptime.addActionListener(this);
        stoptime.setFocusable(false);
        stoptime.setVisible(false);
        add(stoptime);

        JPanel jp = new JPanel();
        jp.setBounds(50, 120, 510, 510);
        jp.setBackground(Color.black);
        jp.setLayout(null);
        add(jp);

        JPanel jp2 = new JPanel();
        jp2.setBounds(45, 115, 515, 515);
        jp2.setBackground(Color.black);
        jp2.setLayout(null);
        add(jp2);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                jp.add(blockNo[i][j]);
            }
        }

        JLabel mistakelbl = new JLabel("Mistakes: ");
        mistakelbl.setBounds(400, 80, 130, 30);
        mistakelbl.setFont(new Font("Tahoma", Font.PLAIN, 23));
        add(mistakelbl);

        mistakecountlbl = new JLabel("0/3");
        mistakecountlbl.setBounds(510, 80, 50, 30);
        mistakecountlbl.setFont(new Font("Tahoma", Font.PLAIN, 23));
        add(mistakecountlbl);

        one = new JButton("1");
        one.setBounds(600, 330, 70, 70);
        one.setFont(new Font("Tahoma", Font.PLAIN, 30));
        one.setFocusable(false);
        one.addActionListener(this);
        add(one);

        two = new JButton("2");
        two.setBounds(680, 330, 70, 70);
        two.setFont(new Font("Tahoma", Font.PLAIN, 30));
        two.setFocusable(false);
        two.addActionListener(this);
        add(two);

        three = new JButton("3");
        three.setBounds(760, 330, 70, 70);
        three.setFont(new Font("Tahoma", Font.PLAIN, 30));
        three.setFocusable(false);
        three.addActionListener(this);
        add(three);

        four = new JButton("4");
        four.setBounds(600, 410, 70, 70);
        four.setFont(new Font("Tahoma", Font.PLAIN, 30));
        four.setFocusable(false);
        four.addActionListener(this);
        add(four);

        five = new JButton("5");
        five.setBounds(680, 410, 70, 70);
        five.setFont(new Font("Tahoma", Font.PLAIN, 30));
        five.setFocusable(false);
        five.addActionListener(this);
        add(five);

        six = new JButton("6");
        six.setBounds(760, 410, 70, 70);
        six.setFont(new Font("Tahoma", Font.PLAIN, 30));
        six.setFocusable(false);
        six.addActionListener(this);
        add(six);

        seven = new JButton("7");
        seven.setBounds(600, 490, 70, 70);
        seven.setFont(new Font("Tahoma", Font.PLAIN, 30));
        seven.setFocusable(false);
        seven.addActionListener(this);
        add(seven);

        eight = new JButton("8");
        eight.setBounds(680, 490, 70, 70);
        eight.setFont(new Font("Tahoma", Font.PLAIN, 30));
        eight.setFocusable(false);
        eight.addActionListener(this);
        add(eight);

        nine = new JButton("9");
        nine.setBounds(760, 490, 70, 70);
        nine.setFont(new Font("Tahoma", Font.PLAIN, 30));
        nine.setFocusable(false);
        nine.addActionListener(this);
        add(nine);

        erase = new JButton("Erase");
        erase.setBounds(600, 180, 110, 50);
        erase.setFont(new Font("Tahoma", Font.PLAIN, 22));
        erase.setFocusable(false);
        erase.addActionListener(this);
        add(erase);

        check = new JButton("Check");
        check.setBounds(750, 180, 110, 50);
        check.setFont(new Font("Tahoma", Font.PLAIN, 22));
        check.setFocusable(false);
        check.addActionListener(this);
        add(check);

        newGame = new JButton("New Game");
        newGame.setBounds(650, 110, 150, 50);
        newGame.setFont(new Font("Tahoma", Font.PLAIN, 22));
        newGame.setFocusable(false);
        newGame.addActionListener(this);
        add(newGame);

        getSolution = new JButton("Get Solution");
        getSolution.setBounds(650, 250, 180, 50);
        getSolution.setFont(new Font("Tahoma", Font.PLAIN, 22));
        getSolution.setFocusable(false);
        getSolution.addActionListener(this);
        add(getSolution);

        ImageIcon cancelimg1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.png"));
        Image cancelimg2 = cancelimg1.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        menu = new JButton("Home", new ImageIcon(cancelimg2));
        menu.setBounds(720, 30, 140, 50);
        menu.setFont(new Font("Tahoma", Font.BOLD, 20));
        menu.addActionListener(this);
        menu.setFocusable(false);
        add(menu);

        setSize(900, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == one) {
            currentBlock.setText(one.getText());
            currentBlock.setForeground(new Color(50, 205, 50));

            if (!validEntry()) {
                currentBlock.setForeground(Color.red);
                mistakeCount++;
                mistakecountlbl.setText(mistakeCount + "/3");
            } else {
                currentBlock.setForeground(new Color(50, 205, 50));
            }
            if (mistakeCount == 3) {
                JOptionPane.showMessageDialog(null, "Game Over!!!");
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        blockNo[i][j].setEnabled(false);
                    }
                }
            }
        } else if (ae.getSource() == two) {
            currentBlock.setText(two.getText());
            currentBlock.setForeground(new Color(50, 205, 50));
            if (!validEntry()) {
                currentBlock.setForeground(Color.red);
                mistakeCount++;
                mistakecountlbl.setText(mistakeCount + "/3");
            } else {
                currentBlock.setForeground(new Color(50, 205, 50));
            }
            if (mistakeCount == 3) {
                JOptionPane.showMessageDialog(null, "Game Over!!!");
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        blockNo[i][j].setEnabled(false);
                    }
                }
            }
        } else if (ae.getSource() == three) {
            currentBlock.setText(three.getText());
            currentBlock.setForeground(new Color(50, 205, 50));
            if (!validEntry()) {
                currentBlock.setForeground(Color.red);
                mistakeCount++;
                mistakecountlbl.setText(mistakeCount + "/3");
            } else {
                currentBlock.setForeground(new Color(50, 205, 50));
            }
            if (mistakeCount == 3) {
                JOptionPane.showMessageDialog(null, "Game Over!!!");
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        blockNo[i][j].setEnabled(false);
                    }
                }
            }
        } else if (ae.getSource() == four) {
            currentBlock.setText(four.getText());
            currentBlock.setForeground(new Color(50, 205, 50));
            if (!validEntry()) {
                currentBlock.setForeground(Color.red);
                mistakeCount++;
                mistakecountlbl.setText(mistakeCount + "/3");
            } else {
                currentBlock.setForeground(new Color(50, 205, 50));
            }
            if (mistakeCount == 3) {
                JOptionPane.showMessageDialog(null, "Game Over!!!");
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        blockNo[i][j].setEnabled(false);
                    }
                }
            }
        } else if (ae.getSource() == five) {
            currentBlock.setText(five.getText());
            currentBlock.setForeground(new Color(50, 205, 50));
            if (!validEntry()) {
                currentBlock.setForeground(Color.red);
                mistakeCount++;
                mistakecountlbl.setText(mistakeCount + "/3");
            } else {
                currentBlock.setForeground(new Color(50, 205, 50));
            }
            if (mistakeCount == 3) {
                JOptionPane.showMessageDialog(null, "Game Over!!!");
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        blockNo[i][j].setEnabled(false);
                    }
                }
            }
        } else if (ae.getSource() == six) {
            currentBlock.setText(six.getText());
            currentBlock.setForeground(new Color(50, 205, 50));
            if (!validEntry()) {
                currentBlock.setForeground(Color.red);
                mistakeCount++;
                mistakecountlbl.setText(mistakeCount + "/3");
            } else {
                currentBlock.setForeground(new Color(50, 205, 50));
            }
            if (mistakeCount == 3) {
                JOptionPane.showMessageDialog(null, "Game Over!!!");
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        blockNo[i][j].setEnabled(false);
                    }
                }
            }
        } else if (ae.getSource() == seven) {
            currentBlock.setText(seven.getText());
            currentBlock.setForeground(new Color(50, 205, 50));
            if (!validEntry()) {
                currentBlock.setForeground(Color.red);
                mistakeCount++;
                mistakecountlbl.setText(mistakeCount + "/3");
            } else {
                currentBlock.setForeground(new Color(50, 205, 50));
            }
            if (mistakeCount == 3) {
                JOptionPane.showMessageDialog(null, "Game Over!!!");
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        blockNo[i][j].setEnabled(false);
                    }
                }
            }
        } else if (ae.getSource() == eight) {
            currentBlock.setText(eight.getText());
            currentBlock.setForeground(new Color(50, 205, 50));
            if (!validEntry()) {
                currentBlock.setForeground(Color.red);
                mistakeCount++;
                mistakecountlbl.setText(mistakeCount + "/3");
            } else {
                currentBlock.setForeground(new Color(50, 205, 50));
            }
            if (mistakeCount == 3) {
                JOptionPane.showMessageDialog(null, "Game Over!!!");
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        blockNo[i][j].setEnabled(false);
                    }
                }
            }
        } else if (ae.getSource() == nine) {
            currentBlock.setText(nine.getText());
            currentBlock.setForeground(new Color(50, 205, 50));
            if (!validEntry()) {
                currentBlock.setForeground(Color.red);
                mistakeCount++;
                mistakecountlbl.setText(mistakeCount + "/3");
            } else {
                currentBlock.setForeground(new Color(50, 205, 50));
            }
            if (mistakeCount == 3) {
                JOptionPane.showMessageDialog(null, "Game Over!!!");
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        blockNo[i][j].setEnabled(false);
                    }
                }
            }
        } else if (ae.getSource() == erase) {
            currentBlock.setText("");
        } else if (ae.getSource() == check) {
            if (checkSolution(puzzle)) {
                JOptionPane.showMessageDialog(null, "Valid");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid");
            }
            //printSudoku(puzzle);
        } else if (ae.getSource() == newGame) {
            reset();
            start();
            stoptime.setVisible(true);
            starttime.setVisible(false);

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    blockNo[i][j].setVisible(true);
                }
            }

            mistakeCount = 0;
            mistakecountlbl.setText(mistakeCount + "/3");

            //this for loop is created to assign enabled textfield with true and disabled with false(by default)
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    flag[i][j] = true;
                }
            }
            puzzle = Puzzle.getPuzzle();
            fillBox(puzzle);;
        } else if (ae.getSource() == getSolution) {
            reset();
            fillSolution(puzzle);
            //printSudoku(puzzle);
        } else if (ae.getSource() == menu) {
            reset();
            this.setVisible(false);
            new Menu();
        } else if (ae.getSource() == starttime) {
            start();
            starttime.setVisible(false);
            stoptime.setVisible(true);

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    blockNo[i][j].setVisible(true);
                }
            }
        } else if (ae.getSource() == stoptime) {
            stop();
            starttime.setVisible(true);
            stoptime.setVisible(false);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    blockNo[i][j].setVisible(false);
                }
            }
        }
    }

    //for testing purpose only
//    public void printSudoku(int[][] puzzle) {
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                System.out.print(puzzle[i][j] + " ");
//            }
//        }
//    }
    public void fillSolution(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (flag[i][j]) {
                    blockNo[i][j].setText(board[i][j] + "");
                    blockNo[i][j].setForeground(new Color(20, 180, 5));
                }
            }
        }
    }

    //to choose visible number randomly
    public JTextField randomBox() {
        Random rand = new Random();
        int i = rand.nextInt(9);
        int j = rand.nextInt(9);

        return blockNo[i][j];
    }

    public void fillBox(int[][] puzzle) {

        if ((difficulty.getSelectedItem()).equals("Easy")) {
            difficultyCount = 50;
        } else if ((difficulty.getSelectedItem()).equals("Medium")) {
            difficultyCount = 42;
        } else {
            difficultyCount = 35;
        }

        int count = 0;

        //to remove the problem of overlapping of box background colors
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                blockNo[i][j].setBackground(Color.white);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                blockNo[i][j].setText(puzzle[i][j] + "");
                if (count < difficultyCount) {
                    JTextField box = new JTextField();
                    box = randomBox();
                    box.setBackground(new Color(94, 181, 206));
                    box.setDisabledTextColor(Color.black);
                    box.setEnabled(false);
                    box.setEditable(false);
                    count++;
                }
            }
        }

        //assign flag to every jtextfield to indentify
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (blockNo[i][j].getBackground().equals(new Color(94, 181, 206))) {
                    flag[i][j] = false;
                }
            }
        }

        //for testing purpose
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                System.out.print(flag[i][j] + " ");
//            }
//            System.out.println();
//        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (flag[i][j]) {
                    blockNo[i][j].setText("");
                    blockNo[i][j].setFont(new Font("Tahoma", Font.PLAIN, 25));
                    blockNo[i][j].setForeground(Color.white);
                    blockNo[i][j].setEditable(true);
                    blockNo[i][j].setEnabled(true);
                }
            }
        }

//        printSudoku(puzzle);
        //System.out.println(SudokuValidator.isValidSudoku(puzzle)); //to check if the generated sudoku is valid or not
    }

    public boolean validEntry() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (blockNo[i][j].getText().equals("")) {
                    entry[i][j] = 0;
                } else {
                    entry[i][j] = Integer.parseInt(blockNo[i][j].getText());
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (entry[i][j] != 0) {
                    if (entry[i][j] != puzzle[i][j]) {
                        return false;
                    }
                }
            }
        }

        return true;
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

        if (SudokuValidator.isValidSudoku(sol)) {
            return true;
        } else {
            return false;
        }
    }

    void start() {
        timer.start();
    }

    void stop() {
        timer.stop();
    }

    void reset() {
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;

        String seconds_string = String.format("%02d", seconds);
        String minutes_string = String.format("%02d", minutes);
        String hours_string = String.format("%02d", hours);

        timeJLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    }

    @Override
    public void focusGained(FocusEvent fe) {
        currentBlock = (JTextField) fe.getSource();
        currentBlock.setBackground(new Color(212, 235, 242));

        Rectangle currentbox = new Rectangle();
        currentbox = currentBlock.getBounds();
        int X = currentbox.x;
        int Y = currentbox.y;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Rectangle box = new Rectangle();
                box = blockNo[i][j].getBounds();

                if (box.x == X || box.y == Y) { // to highlight the row and the coloum of selected box
                    blockNo[i][j].setBackground(new Color(212, 235, 242));//lighter blue

                    //to highlight the 3x3 box 
                    if ((X == 0 || X == 55 || X == 110) && (Y == 0 || Y == 55 || Y == 110)) {

                        for (int s = 0; s < 3; s++) {
                            for (int t = 0; t < 3; t++) {
                                blockNo[s][t].setBackground(new Color(212, 235, 242));
                            }
                        }
                    } else if ((X == 0 || X == 55 || X == 110) && (Y == 170 || Y == 225 || Y == 280)) {

                        for (int s = 3; s < 6; s++) {
                            for (int t = 0; t < 3; t++) {
                                blockNo[s][t].setBackground(new Color(212, 235, 242));
                            }
                        }
                    } else if ((X == 0 || X == 55 || X == 110) && (Y == 340 || Y == 395 || Y == 450)) {

                        for (int s = 6; s < 9; s++) {
                            for (int t = 0; t < 3; t++) {
                                blockNo[s][t].setBackground(new Color(212, 235, 242));
                            }
                        }

                    } else if ((X == 170 || X == 225 || X == 280) && (Y == 0 || Y == 55 || Y == 110)) {

                        for (int s = 0; s < 3; s++) {
                            for (int t = 3; t < 6; t++) {
                                blockNo[s][t].setBackground(new Color(212, 235, 242));
                            }
                        }
                    } else if ((X == 170 || X == 225 || X == 280) && (Y == 170 || Y == 225 || Y == 280)) {

                        for (int s = 3; s < 6; s++) {
                            for (int t = 3; t < 6; t++) {
                                blockNo[s][t].setBackground(new Color(212, 235, 242));
                            }
                        }
                    } else if ((X == 170 || X == 225 || X == 280) && (Y == 340 || Y == 395 || Y == 450)) {

                        for (int s = 6; s < 9; s++) {
                            for (int t = 3; t < 6; t++) {
                                blockNo[s][t].setBackground(new Color(212, 235, 242));
                            }
                        }

                    } else if ((X == 340 || X == 395 || X == 450) && (Y == 0 || Y == 55 || Y == 110)) {

                        for (int s = 0; s < 3; s++) {
                            for (int t = 6; t < 9; t++) {
                                blockNo[s][t].setBackground(new Color(212, 235, 242));
                            }
                        }
                    } else if ((X == 340 || X == 395 || X == 450) && (Y == 170 || Y == 225 || Y == 280)) {

                        for (int s = 3; s < 6; s++) {
                            for (int t = 6; t < 9; t++) {
                                blockNo[s][t].setBackground(new Color(212, 235, 242));
                            }
                        }
                    } else if ((X == 340 || X == 395 || X == 450) && (Y == 340 || Y == 395 || Y == 450)) {

                        for (int s = 6; s < 9; s++) {
                            for (int t = 6; t < 9; t++) {
                                blockNo[s][t].setBackground(new Color(212, 235, 242));
                            }
                        }
                    }

                }
            }
        }
    }

    @Override
    public void focusLost(FocusEvent fe) {
        currentBlock.setBackground(Color.white);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                blockNo[i][j].setBackground(Color.white);
            }
        }

        Rectangle currentbox = new Rectangle();
        currentbox = currentBlock.getBounds();
        int X = currentbox.x;
        int Y = currentbox.y;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Rectangle box = new Rectangle();
                box = blockNo[i][j].getBounds();

                if ((box.x == X || box.y == Y) && flag[i][j] == false) {
                    blockNo[i][j].setBackground(Color.white);
                }

                if (!flag[i][j]) {
                    blockNo[i][j].setBackground(new Color(94, 181, 206));//darker blue
                }
            }
        }
        currentBlock = null;
    }

    public static void main(String[] args) {
        new Board();
    }
}
