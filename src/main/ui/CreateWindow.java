//package ui;
//
//import model.Expense;
//import model.Expenses;
//
//import javax.swing.*;
//import javax.swing.event.EventListenerList;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.time.LocalDate;
//
//public class CreateWindow implements ActionListener {
//
//    private JFrame frame;
//
//    private JLabel dateLabel;
//    private JLabel categoryLabel;
//    private JLabel amountLabel;
//    private JLabel commentsLabel;
//
//    private JTextField dateTF;
//    private JTextField categoryTF;
//    private JTextField amountTF;
//    private JTextField commentsTF;
//
//    private JButton addBtn;
//    private JButton resetBtn;
//
//    private Expense ex;
//    private Expenses exp;
//
//
//    public CreateWindow(Boolean currentDate) {
//        setFrame();
//
//        setLabel();
//
//        setTextField();
//
//        setButtons();
//
//        addProperties();
//
//        frame.setVisible(true);
//    }
//
//    private void addProperties() {
//        frame.add(dateLabel);
//        frame.add(dateTF);
//        frame.add(categoryLabel);
//        frame.add(categoryTF);
//        frame.add(amountLabel);
//        frame.add(amountTF);
//        frame.add(commentsLabel);
//        frame.add(commentsTF);
//        frame.add(addBtn);
//        frame.add(resetBtn);
//    }
//
//    private void setButtons() {
//        addBtn = new JButton("Add");
//        addBtn.addActionListener(this);
//        resetBtn = new JButton("Reset");
//        resetBtn.addActionListener(this);
//    }
//
//    private void setTextField() {
//        dateTF = new JTextField();
//        dateTF.setText("Insert 'today' to use current date or YYYY-MM-DD)");
//        categoryTF = new JTextField();
//        amountTF = new JTextField();
//        commentsTF = new JTextField();
//    }
//
//    private void setLabel() {
//        dateLabel = new JLabel("Date: ");
//        categoryLabel = new JLabel("Category: ");
//        amountLabel = new JLabel("Amount: ");
//        commentsLabel = new JLabel("Comments: ");
//    }
//
//    private void setFrame() {
//        frame = new JFrame();
//        frame.setSize(450, 300);
//        frame.setLayout(new GridLayout(5, 5));
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == addBtn) {
//            exportExpense();
//            frame.dispose();
//
//        } else if (e.getSource() == resetBtn) {
//            dateTF.setText("");
//            categoryTF.setText("");
//            amountTF.setText("");
//            commentsTF.setText("");
//        }
//    }
//
//    private void exportExpense() {
//        LocalDate date;
//        if (dateTF.getText().toLowerCase() == "today") {
//            date = LocalDate.now();
//        } else {
//            String temp = dateTF.getText();
//            int year = Integer.parseInt(temp.substring(0, 4));
//            int mon = Integer.parseInt(temp.substring(5, 7));
//            int day = Integer.parseInt(temp.substring(8, 10));
//            date = LocalDate.of(year, mon, day);
//        }
//        ex = new Expense(date, categoryTF.getText(), Double.parseDouble(amountTF.getText()), commentsTF.getText());
//        exp.addExpense(ex);
//    }
//}