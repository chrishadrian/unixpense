package ui;

import javax.swing.*;
import java.awt.*;

public class CreateWindow {
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Hello!");

    CreateWindow() {
        label.setBounds(0,0,100,50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        frame.add(label);

        frame.setSize(400,300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
