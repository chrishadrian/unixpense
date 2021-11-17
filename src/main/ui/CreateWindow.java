package ui;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.AttributedString;

public class CreateWindow implements ActionListener {

    private JFrame frame;

    private JLabel dateLabel;
    private JLabel categoryLabel;
    private JLabel amountLabel;
    private JLabel commentsLabel;

    private JTextField dateTF;
    private JTextField categoryTF;
    private JTextField amountTF;
    private JTextField commentsTF;

    private JButton addBtn;
    private JButton resetBtn;

    private EventListenerList listenerList = new EventListenerList();

    public CreateWindow() {
        setFrame();

        setLabel();

        setTextField();

        setButtons();

        frame.add(dateLabel);
        frame.add(dateTF);
        frame.add(categoryLabel);
        frame.add(categoryTF);
        frame.add(amountLabel);
        frame.add(amountTF);
        frame.add(commentsLabel);
        frame.add(commentsTF);
        frame.add(addBtn);
        frame.add(resetBtn);


        frame.setVisible(true);
    }

    private void setButtons() {
        addBtn = new JButton("Add");
        addBtn.addActionListener(this);
        resetBtn = new JButton("Reset");
        resetBtn.addActionListener(this);
    }

    private void setTextField() {
        dateTF = new JTextField();
        dateTF.setText("Insert 'today' to use current date");
        categoryTF = new JTextField();
        amountTF = new JTextField();
        commentsTF = new JTextField();
    }

    private void setLabel() {
        dateLabel = new JLabel("Date: ");
        categoryLabel = new JLabel("Category: ");
        amountLabel = new JLabel("Amount: ");
        commentsLabel = new JLabel("Comments: ");
    }

    private void setFrame() {
        frame = new JFrame();
        frame.setSize(450,300);
        frame.setLayout(new GridLayout(5,5));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            System.out.println("Add button clicked!");
            frame.dispose();
        } else if (e.getSource() == resetBtn) {
            System.out.println("Reset button clicked!");
        }
    }
}