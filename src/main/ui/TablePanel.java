package ui;

import model.Expense;
import model.Expenses;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class TablePanel extends JPanel {

    private Expenses exp;
    private boolean debugToggle = false;
    private JTable expTable;

    public TablePanel() {
        super(new GridLayout(1,0));

        this.exp = exp;
        this.exp.addExpense(new Expense(LocalDate.now(), "Groceries", 32, ""));
        this.exp.addExpense(new Expense(LocalDate.now(), "Personal", 32, ""));

        String[] columnNames = {"Date", "Category", "Amount", "Comments"};
        setTable(columnNames);

        debugTable();

        printExpenses(columnNames);
    }

    private void printExpenses(String[] columnNames) {
        DefaultTableModel model = new DefaultTableModel();
        expTable.setModel(model);

        model.setColumnIdentifiers(columnNames);

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

    private void debugTable() {
        if (debugToggle) {
            expTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(expTable);
                }
            });
        }
    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}