package ui;

import model.Event;
import model.EventLog;
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
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents application's main window frame.
 */
public class UnixpenseGUI extends JFrame {

    private final ImagePanel imagePanel;
    private final TablePanel tablePanel;
    private final ButtonsPanel buttonsPanel;

    private Expenses exp;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final String JSON_STORE = "./data/expenses.json";

    // EFFECTS: displays the main frame to the screen
    UnixpenseGUI() {
        super("Unixpense: University Expense");

        EventLog.getInstance().logEvent(new Event("Application started."));

        new LoadWindow();
        setFrame();

        // Initialize expenses and JSON
        exp = new Expenses();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

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

        printLog();

    }

    private void setFrame() {
        this.setSize(600,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void printLog() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog.getInstance().logEvent(new Event("Application closed."));
                printLog(EventLog.getInstance());
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }

            public void printLog(EventLog el) {
                for (Event next : el) {
                    System.out.println(next.toString() + "\n");
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: loads expenses from JSON_STORE
    private void loadExpenses() {
        try {
            exp = jsonReader.read();
            tablePanel.updateExpenses("");

            EventLog.getInstance().logEvent(new Event("Loaded Expenses from " + JSON_STORE + "."));
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves expenses to JSON_STORE
    private void saveExpenses() {
        try {
            EventLog.getInstance().logEvent(new Event("Saved Expenses to " + JSON_STORE + "."));
            jsonWriter.open();
            jsonWriter.write(exp);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    /**
     * Represents application's load window frame that is opened at the start of the program.
     */
    private class LoadWindow implements ActionListener {

        private final JButton yesBtn;
        private final JButton noBtn;
        private JFrame frame;

        // EFFECTS: displays load window frame to the screen
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
                loadExpenses();
                frame.dispose();
            } else if (e.getSource() == noBtn) {
                frame.dispose();
            }
        }
    }

    /**
     * Represents application's image panel that is located at the top of the main frame
     */
    private static class ImagePanel extends JPanel {
        ImageIcon image;
        JLabel titleLabel;
        JLabel iconLabel;

        // EFFECTS: displays image panel to the main frame
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

        // MODIFIES: this
        // EFFECTS: resize image to smaller scale
        private void resizeImage() {
            Image img = image.getImage();
            Image newImg = img.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
            image = new ImageIcon(newImg);
        }
    }

    /**
     * Represents application's table panel that is located at the center of the main frame.
     */
    private class TablePanel extends JPanel {

        private JTable expTable;
        private final DefaultTableModel model;
        List<Expense> printedExp;

        // EFFECTS: displays table panel to the main frame
        public TablePanel() {
            super(new GridLayout(1, 0));

            String[] columnNames = {"Date", "Category", "Amount", "Comments"};
            setTable(columnNames);

            model = new DefaultTableModel();
            expTable.setModel(model);

            model.setColumnIdentifiers(columnNames);
        }

        // MODIFIES: MainFrame.this and this
        // EFFECTS: sort all expenses based on date and print to the table
        private void updateExpenses(String category) {
            model.setRowCount(0);
            if (category.equals("") || category.equals("Filter by:")) {
                exp.sortExpensesDate();
            }

            printedExp = exp.getExpenses();
            printedExp = getNewExpenses(category, printedExp);

            for (Expense ex : printedExp) {
                Object[] o = new Object[4];
                o[0] = ex.getDate();
                o[1] = ex.getCategory();
                o[2] = ex.getAmount();
                o[3] = ex.getComment();
                model.addRow(o);
            }
        }

        // EFFECTS: return a new filtered list of expenses based on category
        private List<Expense> getNewExpenses(String category, List<Expense> printedExp) {
            if (category.equals("") || category.equals("Filter by:")) {
                printedExp = exp.getExpenses();
            } else if (category.equals("Groceries")) {
                printedExp = filterExpenses(printedExp, "Groceries");
            } else if (category.equals("Food")) {
                printedExp = filterExpenses(printedExp, "Food");
            } else if (category.equals("Transportation")) {
                printedExp = filterExpenses(printedExp, "Transportation");
            } else if (category.equals("Personal")) {
                printedExp = filterExpenses(printedExp, "Personal");
            } else if (category.equals("Hangout")) {
                printedExp = filterExpenses(printedExp, "Hangout");
            } else if (category.equals("Health")) {
                printedExp = filterExpenses(printedExp, "Health");
            }
            return printedExp;
        }

        // EFFECTS: return filtered Expenses based on category
        private List<Expense> filterExpenses(List<Expense> printedExp, String category) {
            return printedExp.stream().filter((Expense ex) ->
                    ex.getCategory().equals(category)).collect(Collectors.toList());
        }

        // EFFECTS: set table settings
        private void setTable(String[] columnNames) {
            Object[][] data = {};
            expTable = new JTable(data, columnNames);
            expTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
            expTable.setFillsViewportHeight(true);
            add(new JScrollPane(expTable));
        }

        // MODIFIES: this
        // EFFECTS: remove selected row
        private void deleteSelectedRow() {
            int getSelectedRowForDeletion = expTable.getSelectedRow();
            if (getSelectedRowForDeletion != -1) {
                model.removeRow(getSelectedRowForDeletion);
                exp.deleteExpense(getSelectedRowForDeletion);
                JOptionPane.showMessageDialog(null, "Expense Deleted.");
            } else {
                JOptionPane.showMessageDialog(null, "Unable To Delete.", "Message", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Represents application's buttons panel that is located at the bottom of the main frame.
     */
    private class ButtonsPanel extends JPanel implements ActionListener {

        private JButton createBtn;
        private JButton deleteBtn;
        private JButton statsBtn;
        private JButton saveBtn;

        private JComboBox filterCB;

        // EFFECTS: displays buttons panel that is located at the bottom of the main frame
        public ButtonsPanel() {
            setPanel();
            setButton();
        }

        // EFFECTS: set ButtonsPanel panel settings
        private void setPanel() {
            Dimension size = getPreferredSize();
            size.width = 800;
            size.height = 100;
            setSize(size);
            setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        }

        // EFFECTS: initialize and add action listener to the buttons and add them to the panel
        private void setButton() {
            createBtn = new JButton("Create");
            deleteBtn = new JButton("Delete");
            statsBtn = new JButton("Statistics");
            saveBtn = new JButton("Save");

            String[] categoryStrings = { "Filter by:", "Groceries", "Food",
                    "Transportation", "Personal", "Hangout", "Health"};
            filterCB = new JComboBox(categoryStrings);
            filterCB.setSelectedIndex(0);

            createBtn.addActionListener(this);
            deleteBtn.addActionListener(this);
            statsBtn.addActionListener(this);
            saveBtn.addActionListener(this);
            filterCB.addActionListener(this);

            add(createBtn);
            add(deleteBtn);
            add(statsBtn);
            add(filterCB);
            add(saveBtn);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == createBtn) {
                new DateWindow();
            } else if (e.getSource() == deleteBtn) {
                tablePanel.deleteSelectedRow();
            } else if (e.getSource() == statsBtn) {
                if (exp.length() == 0 || tablePanel.printedExp.size() == 0) {
                    JOptionPane.showMessageDialog(null, "There is no data in the table!",
                                "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
                } else {
                    new StatsWindow();
                }
            } else if (e.getSource() == saveBtn) {
                saveExpenses();
            } else {
                JComboBox cb = (JComboBox)e.getSource();
                String category = (String)cb.getSelectedItem();
                tablePanel.updateExpenses(category);
            }
        }
    }

    /**
     * Represents application's date window that will pop up when createBtn is clicked
     */
    public class DateWindow implements ActionListener {

        private JFrame frame;

        private final JButton yesBtn;
        private final JButton noBtn;

        // EFFECTS: pops up date window frame
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
                new CreateWindow(true);
                frame.dispose();
            } else if (e.getSource() == noBtn) {
                new CreateWindow(false);
                frame.dispose();
            }
        }
    }

    /**
     * Represents application's create window that will show after either dateWindow's buttons is clicked
     */
    public class CreateWindow implements ActionListener {

        private JFrame frame;

        private JLabel dateLabel;
        private JLabel categoryLabel;
        private JLabel amountLabel;
        private JLabel commentsLabel;

        private JTextField dateTF;
        private JComboBox categoryCB;
        private JTextField amountTF;
        private JTextField commentsTF;

        private JButton addBtn;
        private JButton resetBtn;

        private final Boolean currentDate;

        // EFFECTS: pops up create window frame
        public CreateWindow(Boolean currentDate) {
            this.currentDate = currentDate;

            setFrame();
            setLabel();
            setTextField();
            setButtons();
            addProperties();
        }

        // EFFECTS: set create window frame's settings
        private void setFrame() {
            frame = new JFrame();
            frame.setSize(450, 300);
            frame.setLayout(new GridLayout(5, 5));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        }

        // EFFECTS: initialize labels
        private void setLabel() {
            dateLabel = new JLabel("Date (YYYY-MM-DD) : ");
            categoryLabel = new JLabel("Category : ");
            amountLabel = new JLabel("Amount : ");
            commentsLabel = new JLabel("Comments : ");
        }

        // EFFECTS: initialize text fields
        private void setTextField() {
            dateTF = new JTextField();
            if (currentDate) {
                dateTF.setText(String.valueOf(LocalDate.now()));
            }
            String[] categoryStrings = { "Groceries", "Food", "Transportation", "Personal", "Hangout", "Health"};
            categoryCB = new JComboBox(categoryStrings);
            categoryCB.setSelectedIndex(-1);
            amountTF = new JTextField();
            commentsTF = new JTextField();
        }

        // EFFECTS: initialize buttons
        private void setButtons() {
            addBtn = new JButton("Add");
            addBtn.addActionListener(this);
            resetBtn = new JButton("Reset");
            resetBtn.addActionListener(this);
        }

        // EFFECTS: add labels, text fields, and buttons to the frame
        private void addProperties() {
            frame.add(dateLabel);
            frame.add(dateTF);
            frame.add(categoryLabel);
            frame.add(categoryCB);
            frame.add(amountLabel);
            frame.add(amountTF);
            frame.add(commentsLabel);
            frame.add(commentsTF);
            frame.add(addBtn);
            frame.add(resetBtn);
        }

        // MODIFIES: MainFrame.this
        // EFFECTS: set a new expense with text fields' inputs and store it into expenses list
        private void exportExpense() {
            String temp = dateTF.getText();
            int year = Integer.parseInt(temp.substring(0, 4));
            int mon = Integer.parseInt(temp.substring(5, 7));
            int day = Integer.parseInt(temp.substring(8, 10));
            LocalDate date = LocalDate.of(year, mon, day);


            if (categoryCB.getSelectedIndex() == -1) {
                throw new RuntimeException("Do not leave category field blank!");
            } else {
                String categoryString = (String) categoryCB.getSelectedItem();
                Expense ex = new Expense(date, categoryString,
                        Double.parseDouble(amountTF.getText()), commentsTF.getText());
                exp.addExpense(ex);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addBtn) {
                try {
                    exportExpense();
                    tablePanel.updateExpenses("");
                    frame.dispose();
                } catch (StringIndexOutOfBoundsException er) {
                    JOptionPane.showMessageDialog(null, "Date format: YYYY-MM-DD!",
                            "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException er) {
                    JOptionPane.showMessageDialog(null, "Please input number in amount field!",
                            "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
                } catch (RuntimeException er) {
                    JOptionPane.showMessageDialog(null, er.getMessage(),
                            "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
                }

            } else if (e.getSource() == resetBtn) {
                dateTF.setText("");
                categoryCB.setSelectedIndex(-1);
                amountTF.setText("");
                commentsTF.setText("");
            }
        }
    }

    /**
     * Represents application's stats window frame that will show after statsBtn is clicked
     */
    public class StatsWindow implements ActionListener {

        private JFrame frame;

        private final JLabel statsLabel;
        private final JLabel sumLabel;
        private final JLabel meanLabel;
        private final JLabel medianLabel;

        private final JButton okBtn;

        private final List<Double> amounts;
        private final DecimalFormat df = new DecimalFormat("0.00");

        private List<Expense> tableExpenses = tablePanel.printedExp;

        // EFFECTS: pops up stats window frame
        public StatsWindow() {
            setFrame();

            amounts = new ArrayList<>();

            statsLabel = new JLabel("Statistics");
            statsLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
            statsLabel.setHorizontalAlignment(SwingConstants.CENTER);

            df.setRoundingMode(RoundingMode.UP);
            sumLabel = new JLabel(" Sum of expenses: " + sumExpenses());
            meanLabel = new JLabel(" Mean of expenses: " + meanExpenses());
            medianLabel = new JLabel(" Median of expenses: " + medianExpenses());

            JPanel statsPanel = new JPanel(new BorderLayout());
            statsPanel.add(sumLabel, BorderLayout.NORTH);
            statsPanel.add(meanLabel, BorderLayout.CENTER);
            statsPanel.add(medianLabel, BorderLayout.SOUTH);

            okBtn = new JButton("I'M BROKE AF T_T");
            okBtn.addActionListener(this);
            okBtn.setSize(100, 40);

            Container c = frame.getContentPane();
            c.add(statsLabel, BorderLayout.NORTH);
            c.add(statsPanel, BorderLayout.CENTER);
            c.add(okBtn, BorderLayout.SOUTH);
        }

        // EFFECTS: return the sum of amounts in expenses in 2 decimal places
        private String sumExpenses() {
            double sum = 0;

            for (Expense ex : tableExpenses) {
                sum = sum + ex.getAmount();
            }
            return df.format(sum);
        }

        // EFFECTS: return the mean of the amounts in expenses in 2 decimal places
        private String meanExpenses() {
            double mean = Double.parseDouble(sumExpenses()) / tableExpenses.size();
            return df.format(mean);
        }

        // EFFECTS: return the median of the amounts in expenses in 2 decimal places
        private String medianExpenses() {
            double median;

            getSortedAmounts();

            if (amounts.size() % 2 == 0) {
                median = (amounts.get(amounts.size() / 2) + amounts.get(amounts.size() / 2 - 1)) / 2;
            } else {
                median = amounts.get(amounts.size() / 2);
            }

            return df.format(median);
        }

        // MODIFIES: this
        // EFFECTS: helper function to get a sorted values of amounts in expenses
        private void getSortedAmounts() {
            amounts.clear();
            for (Expense ex : tablePanel.printedExp) {
                amounts.add(ex.getAmount());
            }
            Collections.sort(amounts);
        }

        // EFFECTS: set stats window's frame settings
        private void setFrame() {
            frame = new JFrame();
            frame.setSize(300, 150);
            frame.setLayout(new BorderLayout(0, 10));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == okBtn) {
                frame.dispose();
            }
        }
    }
}