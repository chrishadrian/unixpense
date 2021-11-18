//package ui;
//
//import javax.swing.*;
//import javax.swing.border.Border;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class DateWindow implements ActionListener {
//
//    private JFrame frame;
//
//    private JLabel askDateLabel;
//    private JButton yesBtn;
//    private JButton noBtn;
//
//    public DateWindow() {
//        setFrame();
//
//        askDateLabel = new JLabel("Do you want to use current date?");
//
//        yesBtn = new JButton("Yes");
//        yesBtn.addActionListener(this);
//        noBtn = new JButton("No");
//        noBtn.addActionListener(this);
//
//        Container c = frame.getContentPane();
//
//        c.add(askDateLabel, BorderLayout.NORTH);
//        c.add(yesBtn, BorderLayout.CENTER);
//        c.add(noBtn, BorderLayout.CENTER);
//    }
//
//    private void setFrame() {
//        frame = new JFrame();
//        frame.setSize(300,100);
//        frame.setLayout(new BorderLayout());
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == yesBtn) {
//            new CreateWindow(true);
//            frame.dispose();
//
//        } else if (e.getSource() == noBtn) {
//            new CreateWindow(false);
//            frame.dispose();
//        }
//    }
//}
