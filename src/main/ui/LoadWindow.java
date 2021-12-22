package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents application's load window frame that is opened at the start of the program.
 */
public class LoadWindow implements ActionListener {

    private final JButton yesBtn;
    private final JButton noBtn;
    private JFrame frame;
    private UnixpenseGUI main;

    // EFFECTS: displays load window frame to the screen
    public LoadWindow(UnixpenseGUI gui) {
        setFrame();

        JLabel askLoadLabel = new JLabel("Do you want to load previous expenses?");
        askLoadLabel.setHorizontalAlignment(SwingConstants.CENTER);

        yesBtn = new JButton("Yes");
        yesBtn.addActionListener(this);
        noBtn = new JButton("No");
        noBtn.addActionListener(this);
        JPanel yesNoPanel = new JPanel(new FlowLayout());
        yesNoPanel.add(yesBtn);
        yesNoPanel.add(noBtn);

        Container c = frame.getContentPane();
        c.add(askLoadLabel, BorderLayout.CENTER);
        c.add(yesNoPanel, BorderLayout.SOUTH);

        main = gui;
    }


    // EFFECTS: helper function to set the frame's settings
    private void setFrame() {
        frame = new JFrame();
        frame.setSize(300, 100);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.toFront();
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yesBtn) {
            main.loadExpenses();
            frame.dispose();
        } else if (e.getSource() == noBtn) {
            frame.dispose();
        }
    }
}