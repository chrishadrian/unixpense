package ui;

import javax.swing.*;
import java.awt.*;

public class UnixpenseGUI extends JFrame {

    JPanel sortPanel;
    JPanel tablePanel;
    JPanel buttonsPanel;

    UnixpenseGUI() {
        super("Unixpense");

//        ImageIcon image = new ImageIcon("./data/expenses.png");

        JLabel label = new JLabel();
        label.setText("Unixpense");
        label.setHorizontalTextPosition(JLabel.LEFT);
        label.setVerticalTextPosition(JLabel.TOP);

        setPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout());
        this.setSize(800,600);
        this.setResizable(false);
        this.setVisible(true);

        sortPanel.add(label);
        this.add(sortPanel);
        this.add(tablePanel);
        this.add(buttonsPanel);

    }

    public void setPanel() {
        sortPanel = new JPanel();
        sortPanel.setBackground(Color.red);
        sortPanel.setBounds(0, 0, 800, 200);

        tablePanel = new JPanel();
        tablePanel.setBounds(0, 200, 800, 200);

        buttonsPanel = new JPanel();
        tablePanel.setBounds(0, 400, 800, 200);
    }
}
