package ui;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    ImageIcon image;
    JLabel label;

    ImagePanel() {
        image = new ImageIcon("./data/expenses.png");
        label = new JLabel();
        label.setText("Unixpense");
//        label.setHorizontalTextPosition(JLabel.LEFT);
//        label.setVerticalTextPosition(JLabel.TOP);
        setLayout(new FlowLayout());

    }
}
