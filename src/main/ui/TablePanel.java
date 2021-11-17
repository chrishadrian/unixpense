package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TablePanel extends JPanel {

    private boolean debugToggle = false;

    private JTable expTable;

    public TablePanel() {
        super(new GridLayout(1,0));

        String[] columnNames = {"Date",
                "Category",
                "Amount",
                "Comments"};

        Object[][] data = {
                {"Kathy", "Smith", new Integer(5), "a"},
                {"John", "Doe", new Integer(3), "a"},
                {"Sue", "Black", new Integer(2), "a"},
                {"Jane", "White", new Integer(20), "a"},
                {"Joe", "Brown", new Integer(10), "a"}
        };

        setTable(columnNames, data);

        debugTable();

        add(new JScrollPane(expTable));
    }

    private void setTable(String[] columnNames, Object[][] data) {
        expTable = new JTable(data, columnNames);
        expTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        expTable.setFillsViewportHeight(true);
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