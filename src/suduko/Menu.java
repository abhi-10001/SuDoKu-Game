/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package suduko;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author abhinendra
 */
public class Menu extends JFrame implements ActionListener {

    JButton play, getSolution, exit;

    Menu() {
        setLayout(null);

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons/play.png"));
        Image img2 = img1.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        play = new JButton("   Play      ", new ImageIcon(img2));
        play.setBounds(100, 50, 200, 50);
        play.setFont(new Font("Tahoma", Font.BOLD, 20));
        play.addActionListener(this);
        play.setFocusable(false);
        add(play);

        ImageIcon solutionimg1 = new ImageIcon(ClassLoader.getSystemResource("icons/solution.png"));
        Image solutionimg2 = solutionimg1.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        getSolution = new JButton("Get Solution", new ImageIcon(solutionimg2));
        getSolution.setBounds(100, 150, 200, 50);
        getSolution.setFont(new Font("Tahoma", Font.BOLD, 20));
        getSolution.addActionListener(this);
        getSolution.setFocusable(false);
        add(getSolution);

        ImageIcon cancelimg1 = new ImageIcon(ClassLoader.getSystemResource("icons/cancel.png"));
        Image cancelimg2 = cancelimg1.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        exit = new JButton(" Exit Game    ", new ImageIcon(cancelimg2));
        exit.setBounds(100, 250, 200, 50);
        exit.setFont(new Font("Tahoma", Font.BOLD, 20));
        exit.addActionListener(this);
        exit.setFocusable(false);
        add(exit);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/background.png"));
        Image i2 = i1.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 400, 400);
        add(image);

        setSize(400, 400);
        setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == play) {
            this.setVisible(false);
            new Board();
        } else if (ae.getSource() == getSolution) {
            this.setVisible(false);
            new FindSolution();
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Menu();
    }
}
