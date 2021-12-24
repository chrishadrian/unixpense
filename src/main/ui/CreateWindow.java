package ui;

import model.Expense;
import model.Expenses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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

    private UnixpenseGUI main;
    private Expenses exp;
    private TablePanel tablePanel;

    // EFFECTS: pops up create window frame
    public CreateWindow(UnixpenseGUI main, Boolean currentDate) {
        this.currentDate = currentDate;

        setFrame();
        setLabel();
        setTextField();
        setButtons();
        addProperties();

        this.main = main;
        this.exp = main.getExpenses();
        this.tablePanel = main.getTablePanel();
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
    // EFFECTS: set a new expense with text fields' inputs and store it into list of expenses
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
            main.setExpenses(exp);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            try {
                exportExpense();
                tablePanel.updateExpenses(exp, "");
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
