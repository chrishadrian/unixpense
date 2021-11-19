package ui;

import model.Expense;
import model.Expenses;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class MainFrame extends JFrame {

    private final ImagePanel imagePanel;
    private final TablePanel tablePanel;
    private final ButtonsPanel buttonsPanel;

    private Expenses exp;
    private DefaultTableModel model;

    private static final String JSON_STORE = "./data/expenses.json";

    MainFrame(String title) {
        super(title);

        new LoadWindow();

        // Initialize expenses
        exp = new Expenses();

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

    private void loadExpenses() {
        JsonReader jsonReader = new JsonReader(JSON_STORE);
        try {
            exp = jsonReader.read();
            tablePanel.updateExpenses();
            System.out.println("Loaded Expenses from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private void saveExpenses() {
        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
        try {
            System.out.println("Saving expenses to " + JSON_STORE + "...");
            jsonWriter.open();
            jsonWriter.write(exp);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private class LoadWindow implements ActionListener {

        private final JButton yesBtn;
        private final JButton noBtn;
        private JFrame frame;

        public LoadWindow() {
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
        }

        private void setFrame() {
            frame = new JFrame();
            frame.setSize(300,100);
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
                loadExpenses();
                frame.dispose();
            } else if (e.getSource() == noBtn) {
                frame.dispose();
            }
        }
    }

    private static class ImagePanel extends JPanel {
        ImageIcon image;
        JLabel titleLabel;
        JLabel iconLabel;

        ImagePanel() {
            image = new ImageIcon("./data/expenses.png");
            resizeImage();
            iconLabel = new JLabel(image);

            titleLabel = new JLabel();
            titleLabel.setText("Unixpense");
            titleLabel.setFont(new Font("Verdana", Font.ITALIC, 25));

            setLayout(new FlowLayout());
            add(iconLabel);
            add(titleLabel);
        }

        private void resizeImage() {
            Image img = image.getImage();
            Image newImg = img.getScaledInstance(80,80,Image.SCALE_DEFAULT);
            image = new ImageIcon(newImg);
        }
    }

    private class TablePanel extends JPanel {

        private JTable expTable;

        public TablePanel() {
            super(new GridLayout(1,0));

            String[] columnNames = {"Date", "Category", "Amount", "Comments"};
            setTable(columnNames);

            model = new DefaultTableModel();
            expTable.setModel(model);

            model.setColumnIdentifiers(columnNames);
        }

        private void updateExpenses() {
            model.setRowCount(0);
            exp.sortExpensesDate();
            for (int i = 0; i < exp.length(); i++) {
                Expense ex = exp.getExpense(i);
                Object[] o = new Object[4];
                o[0] = ex.getDate();
                o[1] = ex.getCategory();
                o[2] = ex.getAmount();
                o[3] = ex.getComment();
                model.addRow(o);
            }
        }

        private void setTable(String[] columnNames) {
            Object[][] data = {};
            expTable = new JTable(data, columnNames);
            expTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
            expTable.setFillsViewportHeight(true);
            add(new JScrollPane(expTable));
        }

        private void deleteSelectedRow() {
            int getSelectedRowForDeletion = expTable.getSelectedRow();
            if (getSelectedRowForDeletion != -1) {
                model.removeRow(getSelectedRowForDeletion);
                JOptionPane.showMessageDialog(null, "Row Deleted");
            } else {
                JOptionPane.showMessageDialog(null, "Unable To Delete");
            }
        }
    }

    private class ButtonsPanel extends JPanel implements ActionListener {

        private final JButton createBtn;
        private final JButton deleteBtn;
        private final JButton saveBtn;

        public ButtonsPanel() {
            Dimension size = getPreferredSize();
            size.width = 800;
            size.height = 100;
            setSize(size);

            createBtn = new JButton("Create");
            deleteBtn = new JButton("Delete");
            saveBtn = new JButton("Save");

            createBtn.addActionListener(this);
            deleteBtn.addActionListener(this);
            saveBtn.addActionListener(this);

            setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

            add(createBtn);
            add(deleteBtn);
            add(saveBtn);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == createBtn) {
                new DateWindow();
            } else if (e.getSource() == deleteBtn) {
                tablePanel.deleteSelectedRow();
            } else if (e.getSource() == saveBtn) {
                saveExpenses();
            }
        }
    }

    public class DateWindow implements ActionListener {

        private JFrame frame;

        private final JButton yesBtn;
        private final JButton noBtn;

        public DateWindow() {
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
        }

        private void setFrame() {
            frame = new JFrame();
            frame.setSize(300,100);
            frame.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == yesBtn) {
                new CreateWindow(true);
                frame.dispose();
            } else if (e.getSource() == noBtn) {
                new CreateWindow(false);
                frame.dispose();
            }
        }
    }

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

        private final Boolean currentDate;

        public CreateWindow(Boolean currentDate) {
            this.currentDate = currentDate;
            setFrame();

            setLabel();

            setTextField();

            setButtons();

            addProperties();

            frame.setVisible(true);
        }

        private void addProperties() {
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
        }

        private void setButtons() {
            addBtn = new JButton("Add");
            addBtn.addActionListener(this);
            resetBtn = new JButton("Reset");
            resetBtn.addActionListener(this);
        }

        private void setTextField() {
            dateTF = new JTextField();
            if (currentDate) {
                dateTF.setText(String.valueOf(LocalDate.now()));
            }

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

        private void exportExpense() {
            String temp = dateTF.getText();
            int year = Integer.parseInt(temp.substring(0, 4));
            int mon = Integer.parseInt(temp.substring(5, 7));
            int day = Integer.parseInt(temp.substring(8, 10));
            LocalDate date = LocalDate.of(year, mon, day);

            Expense ex = new Expense(date, categoryTF.getText(),
                    Double.parseDouble(amountTF.getText()), commentsTF.getText());
            exp.addExpense(ex);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addBtn) {
                exportExpense();
                tablePanel.updateExpenses();
                frame.dispose();

            } else if (e.getSource() == resetBtn) {
                dateTF.setText("");
                categoryTF.setText("");
                amountTF.setText("");
                commentsTF.setText("");
            }
        }
    }
}