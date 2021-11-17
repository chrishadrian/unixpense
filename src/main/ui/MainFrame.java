package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel sortPanel;
    private JPanel tablePanel;
    private ButtonsPanel buttonsPanel;
    private JPanel createPanel;



    MainFrame(String title) {
        super("Unixpense");

        // Set layout manager
        setLayout(new BorderLayout());

        // Create Swing component
        buttonsPanel = new ButtonsPanel();

        // Add Swing components to content pane
        Container c = getContentPane();

//        c.add(sortPanel, BorderLayout.NORTH);
//        c.add(tablePanel, BorderLayout.CENTER);
        c.add(buttonsPanel, BorderLayout.SOUTH);

    }
}

//        ImageIcon image = new ImageIcon("./data/expenses.png");
//JLabel label = new JLabel();
//        label.setText("Unixpense");
//                label.setHorizontalTextPosition(JLabel.LEFT);
//                label.setVerticalTextPosition(JLabel.TOP);
//frame.dispose();