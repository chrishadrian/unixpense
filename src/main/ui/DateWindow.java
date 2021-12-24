package ui;

import model.Expenses;
import ui.CreateWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents application's date window that will pop up when createBtn is clicked
 */
public class DateWindow implements ActionListener {

    private JFrame frame;

    private JButton yesBtn;
    private JButton noBtn;

    private UnixpenseGUI main;

    // EFFECTS: pops up date window frame
    public DateWindow(UnixpenseGUI main) {
        setFrame();

        JLabel askDateLabel = new JLabel("Do you want to use current date?");
        askDateLabel.setHorizontalAlignment(SwingConstants.CENTER);

        yesBtn = new JButton("Yes");
        yesBtn.addActionListener(this);
        noBtn = new JButton("No");
        noBtn.addActionListener(this);
        JPanel yesNoPanel = new JPanel(new FlowLayout());
        yesNoPanel.add(yesBtn);
        yesNoPanel.add(noBtn);

        Container c = frame.getContentPane();
        c.add(askDateLabel, BorderLayout.CENTER);
        c.add(yesNoPanel, BorderLayout.SOUTH);

        this.main = main;
    }

    // EFFECTS: set frame's settings
    private void setFrame() {
        frame = new JFrame();
        frame.setSize(300, 100);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yesBtn) {
            new CreateWindow(main, true);
            frame.dispose();
        } else if (e.getSource() == noBtn) {
            new CreateWindow(main, false);
            frame.dispose();
        }
    }
}