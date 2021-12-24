package ui;

import model.Event;
import model.EventLog;
import model.Expenses;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

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

        new LoadWindow(this);
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
        buttonsPanel = new ButtonsPanel(this);

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
        this.setResizable(true);
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
    protected void loadExpenses() {
        try {
            exp = jsonReader.read();
            tablePanel.updateExpenses(exp, "");

            EventLog.getInstance().logEvent(new Event("Loaded Expenses from " + JSON_STORE + "."));
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves expenses to JSON_STORE
    protected void saveExpenses() {
        try {
            EventLog.getInstance().logEvent(new Event("Saved Expenses to " + JSON_STORE + "."));
            jsonWriter.open();
            jsonWriter.write(exp);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    public Expenses getExpenses() {
        return exp;
    }

    public void setExpenses(Expenses exp) {
        this.exp = exp;
    }

    public TablePanel getTablePanel() {
        return tablePanel;
    }
}