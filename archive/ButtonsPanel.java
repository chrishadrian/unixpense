//package ui;
//
//import model.Expenses;
//
//import javax.swing.*;
//import javax.swing.event.EventListenerList;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class ButtonsPanel extends JPanel implements ActionListener {
//
//    private EventListenerList listenerList = new EventListenerList();
//
//    private JButton createBtn;
//    private JButton deleteBtn;
//    private JButton loadBtn;
//    private JButton saveBtn;
//
//    private Expenses exp;
//
//    public ButtonsPanel() {
//        Dimension size = getPreferredSize();
//        size.width = 800;
//        size.height = 100;
//        setSize(size);
//
//        createBtn = new JButton("Create");
//        deleteBtn = new JButton("Delete");
//        loadBtn = new JButton("Load");
//        saveBtn = new JButton("Save");
//
//        createBtn.addActionListener(this);
//        deleteBtn.addActionListener(this);
//        loadBtn.addActionListener(this);
//        saveBtn.addActionListener(this);
//
//        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
//
//        add(createBtn);
//        add(deleteBtn);
//        add(loadBtn);
//        add(saveBtn);
//
//        this.exp = exp;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == createBtn) {
//            new CreateWindow(exp);
//        } else if (e.getSource() == deleteBtn) {
//
//        } else if (e.getSource() == loadBtn) {
//
//        } else if (e.getSource() == saveBtn) {
//
//        }
//    }
//}
