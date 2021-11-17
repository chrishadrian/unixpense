package ui;

import model.Expenses;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    ImageIcon image;
    JLabel titleLabel;
    JLabel iconLabel;

    ImagePanel(Expenses exp) {
        image = new ImageIcon("./data/expenses.png");
        resizeImage();
        iconLabel = new JLabel(image);

        titleLabel = new JLabel();
        titleLabel.setText("Unixpense");
        titleLabel.setFont(new Font("Verdana", Font.ITALIC, 25));

        setLayout(new FlowLayout());
        add(iconLabel);
        add(titleLabel);
    }

    private void resizeImage() {
        Image img = image.getImage();
        Image newImg = img.getScaledInstance(80,80,Image.SCALE_DEFAULT);
        image = new ImageIcon(newImg);
    }
}
