package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private ImagePanel imagePanel;
    private TablePanel tablePanel;
    private ButtonsPanel buttonsPanel;
    private JPanel createPanel;



    MainFrame(String title) {
        super("Unixpense");

        // Set layout manager
        setLayout(new BorderLayout());

        // Create Swing component
        imagePanel = new ImagePanel();
        tablePanel = new TablePanel();
        buttonsPanel = new ButtonsPanel();

        // Add Swing components to content pane
        Container c = getContentPane();

        c.add(imagePanel, BorderLayout.NORTH);
        c.add(tablePanel, BorderLayout.CENTER);
        c.add(buttonsPanel, BorderLayout.SOUTH);

    }
}

//frame.dispose();