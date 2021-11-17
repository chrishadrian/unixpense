package ui;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

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
        frame = new JFrame();
        frame.setSize(600,600);
        frame.setLayout(new GridLayout(5,5));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dateLabel = new JLabel("Date: ");
        categoryLabel = new JLabel("Category: ");
        amountLabel = new JLabel("Amount: ");
        commentsLabel = new JLabel("Comments: ");

        dateTF = new JTextField();
        dateTF.setText("Insert 'Today' if you want to use current date");
        categoryTF = new JTextField();
        amountTF = new JTextField();
        commentsTF = new JTextField();

        addBtn = new JButton("Add");
        addBtn.addActionListener(this);
        resetBtn = new JButton("Reset");
        resetBtn.addActionListener(this);

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

//        label.setBounds(0,0,100,50);
//        label.setFont(new Font(null, Font.PLAIN, 25));