package ui;

import model.Expense;
import model.Expenses;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents application's table panel that is located at the center of the main frame.
 */
public class TablePanel extends JPanel {

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
    protected void updateExpenses(Expenses exp, String category) {
        model.setRowCount(0);
        if (category.equals("") || category.equals("Filter by:")) {
            exp.sortExpensesDate();
        }

        printedExp = exp.getExpenses();
        printedExp = getNewExpenses(exp, category, printedExp);

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
    private List<Expense> getNewExpenses(Expenses exp, String category, List<Expense> printedExp) {
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
    protected void deleteSelectedRow(Expenses exp) {
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