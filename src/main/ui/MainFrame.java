package ui;

import model.Expenses;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private ImagePanel imagePanel;
    private TablePanel tablePanel;
    private ButtonsPanel buttonsPanel;

    private Expenses exp;

    MainFrame(String title) {
        super(title);

        // Initialize expenses
        exp = new Expenses();

        // Set layout manager
        setLayout(new BorderLayout());

        // Create Swing component
        imagePanel = new ImagePanel(exp);
        tablePanel = new TablePanel(exp);
        buttonsPanel = new ButtonsPanel(exp);

        // Add Swing components to content pane
        Container c = getContentPane();

        c.add(imagePanel, BorderLayout.NORTH);
        c.add(tablePanel, BorderLayout.CENTER);
        c.add(buttonsPanel, BorderLayout.SOUTH);

    }


}