//package ui;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.time.LocalDate;
//
//public class MainFrame2 {
//
//    private JPanel rootPanel;
//    private JTable showTable;
//    private JButton createButton;
//    private JButton deleteButton;
//    private JButton loadButton;
//    private JButton saveButton;
//    private JComboBox sortByCombo;
//
//
//    public MainFrame2() {
//        setButtonPanel();
//        createTable();
////        createDeleteButton();
////        createSortCombo();
//    }
//
//    private void setButtonPanel() {
//        createButton = new JButton(new CreateButtonAction());
////        deleteButton = new JButton(new DeleteButtonAction());
////        loadButton = new JButton(new LoadButtonAction());
////        saveButton = new JButton(new SaveButtonAction());
//    }
//
//    private void createTable() {
//        Object[][] data = {
//                {LocalDate.now(), "Groceries", 32.2, "First"},
//                {LocalDate.now(), "Personal", 12, "Second"},
//                {LocalDate.now(), "Groceries", 1, "Third"}
//        };
//        showTable.setModel(new DefaultTableModel(
//                data,
//                new String[] {"Date", "Category", "Amount", "Comments"}
//        ));
////        TableColumnModel columns = showTable.getColumnModel();
////        columns.getColumn(0).setMinWidth(100);
//    }
//
//    private class CreateButtonAction extends AbstractAction {
//
//        CreateButtonAction() {
//            super("Create");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
////            CreateFrame frame = new CreateFrame("Create Expense");
////            frame.setVisible(true);
////            frame.pack();
////            frame.setLocationRelativeTo(null);
//            System.out.println("IT WORKS!");
//        }
//    }
//
//
////    public void createCreateGUI() {
////        CreateFrame ui = new CreateFrame();
////        JFrame frame = new JFrame();
////        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////
////        //Create and set up the content pane.
////        JPanel root = ui.getCreatePanel();
////        frame.setContentPane(root);
////
////        //Display the window.
////        frame.pack();
////        frame.setLocationRelativeTo(null);
////        frame.setVisible(true);
////    }
//
//
//    public JPanel getRootPanel() {
//        return rootPanel;
//    }
//
//    private void createUIComponents() {
//        // TODO: place custom component creation code here
//        createButton = new JButton(new CreateButtonAction());
//    }
//}
