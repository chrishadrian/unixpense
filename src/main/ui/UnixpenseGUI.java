package ui;

import javax.swing.*;
import java.awt.*;

public class UnixpenseGUI extends JFrame {

    JPanel sortPanel;
    JPanel tablePanel;
    JPanel buttonsPanel;

    JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.blue);
        bluePanel.setBounds(0, 150, 800, 150);

    JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.green);
        greenPanel.setBounds(0, 300, 800, 150);

    UnixpenseGUI() {
        super("Unixpense");

        ImageIcon image = new ImageIcon("./data/expenses.png");

        JLabel label = new JLabel();
        label.setText("Sort by:");
        label.setIcon(image);
//        label.setHorizontalTextPosition(JLabel.LEFT);
//        label.setVerticalTextPosition(JLabel.TOP);

        sortPanel = new JPanel();
        sortPanel.setBounds(0, 0, 800, 150);

        tablePanel = new JPanel();
        tablePanel.setBounds(0, 150, 800, 150);

        buttonsPanel = new JPanel();
        tablePanel.setBounds(0, 300, 800, 150);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.blue);
        bluePanel.setBounds(0, 150, 800, 150);

        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.green);
        greenPanel.setBounds(0, 300, 800, 150);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(800,600);
        this.setResizable(false);
        this.setVisible(true);

        redPanel.add(label);
        this.add(redPanel);
        this.add(bluePanel);
        this.add(greenPanel);

    }
}
