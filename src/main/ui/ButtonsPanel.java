package ui;

import model.Expenses;
import ui.UnixpenseGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents application's buttons panel that is located at the bottom of the main frame.
 */
public class ButtonsPanel extends JPanel implements ActionListener {

    private JButton createBtn;
    private JButton deleteBtn;
    private JButton statsBtn;
    private JButton saveBtn;

    private JComboBox filterCB;

    private UnixpenseGUI main;
    private Expenses exp;
    private TablePanel tablePanel;

    // EFFECTS: displays buttons panel that is located at the bottom of the main frame
    public ButtonsPanel(UnixpenseGUI gui) {
        setPanel();
        setButton();

        main = gui;
        tablePanel = gui.getTablePanel();
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
            new DateWindow(main);
        } else if (e.getSource() == deleteBtn) {
            tablePanel.deleteSelectedRow(main);
        } else if (e.getSource() == statsBtn) {
            exp = main.getExpenses();
            if (exp.length() == 0 || tablePanel.printedExp.size() == 0) {
                JOptionPane.showMessageDialog(null, "There is no data in the table!",
                        "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
            } else {
                new StatsWindow(main);
            }
        } else if (e.getSource() == saveBtn) {
            main.saveExpenses();
        } else {
            JComboBox cb = (JComboBox)e.getSource();
            String category = (String)cb.getSelectedItem();
            tablePanel.updateExpenses(exp, category);
        }
    }
}