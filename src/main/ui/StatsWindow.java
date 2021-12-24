package ui;

import model.Expense;
import model.Expenses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    private List<Expense> exp;
    private TablePanel tablePanel;

    // EFFECTS: pops up stats window frame
    public StatsWindow(UnixpenseGUI main) {
        setFrame();

        amounts = new ArrayList<>();
        tablePanel = main.getTablePanel();
        exp = tablePanel.getPrintedExp();

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

        for (Expense ex : exp) {
            sum = sum + ex.getAmount();
        }
        return df.format(sum);
    }

    // EFFECTS: return the mean of the amounts in expenses in 2 decimal places
    private String meanExpenses() {
        double mean = Double.parseDouble(sumExpenses()) / exp.size();
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