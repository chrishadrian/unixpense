package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Represents application's image panel that is located at the top of the main frame
 */
public class ImagePanel extends JPanel {
    ImageIcon image;
    JLabel titleLabel;
    JLabel iconLabel;

    // EFFECTS: displays image panel to the main frame
    ImagePanel() {
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

    // MODIFIES: this
    // EFFECTS: resize image to smaller scale
    private void resizeImage() {
        Image img = image.getImage();
        Image newImg = img.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        image = new ImageIcon(newImg);
    }
}