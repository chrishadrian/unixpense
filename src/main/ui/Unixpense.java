package ui;

import model.Expense;
import model.Expenses;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

// Unixpense application
public class Unixpense {
    private static final String JSON_STORE = "./data/expenses.json";
    private Expenses exp;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the Unixpense application
    public Unixpense() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runUnixpense();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runUnixpense() {
        boolean keepGoing = true;
        String command;

        init();
        displayLoad();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("Do you want to save your expenses list before you leave? (Y/N)");
                command = input.next().toLowerCase();
                if (command.equals("y")) {
                    saveExpenses();
                }
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays the first menu when the program has just started
    private void displayLoad() {
        System.out.println("\nUnixpense: University Expense");
        System.out.println("\t-----------------------------");
        System.out.println("\tLoad your previous work? (Y/N)");
        String command = input.next();
        command = command.toLowerCase();
        if (command.equals("y")) {
            loadExpenses();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "v":
                doView();
                break;
            case "c":
                doTrack();
                break;
            case "s":
                saveExpenses();
                break;
            case "d":
                doDelete();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes expenses
    private void init() {
        exp = new Expenses();
        exp.addExpense(new Expense(LocalDate.now(), "Groceries", 32, ""));
        exp.addExpense(new Expense(LocalDate.now(), "Personal", 32, ""));
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> to view your expenses");
        System.out.println("\tc -> to create your expense");
        System.out.println("\tq -> to quit");
    }

    // EFFECTS: display the current view of the expenses list
    private void doView() {
        exp.sortExpensesDate();
        String space = "        ";
        System.out.println("Date" + space + space + "Category" +  space + "  Amount" + space + "Comment");
        double sum = 0;
        for (int i = 0; i < exp.length(); i++) {
            Expense ex = exp.getExpense(i);

            sum = sum + ex.getAmount();
            StringBuilder categorySpace = categorySpace(ex.getCategory().length());
            StringBuilder amountSpace   = amountSpace(String.valueOf(Math.floor((ex.getAmount()))).length());

            System.out.println(ex.getDate() + space + "  " + ex.getCategory() + categorySpace + space
                    + ex.getAmount() + amountSpace + space +  ex.getComment());
        }
        System.out.println("Sum of the month: " + "                    " + sum);
        displayViewMenu();
    }

    // EFFECTS: display menu when user is viewing their expenses list
    private void displayViewMenu() {
        System.out.println("\td -> to delete your i-th expense");
        System.out.println("\tb -> to go back to home menu");

        String command = input.next();
        command.toLowerCase();
        if (command.equals("b")) {
            System.out.println("Returning to menu...");
        } else {
            processCommand(command);
        }
    }

    // EFFECTS: create spaces after printing category in console
    public StringBuilder categorySpace(int len) {
        StringBuilder categorySpace = new StringBuilder();
        int space = 10 - len;
        for (int j = 0; j < space; j++) {
            categorySpace.append(" ");
        }
        return categorySpace;
    }

    // EFFECTS: create spaces after printing amount in console
    public StringBuilder amountSpace(int len) {
        StringBuilder amountSpace = new StringBuilder();
        int amountDiff = 5 - len;
        for (int j = 0; j < amountDiff; j++) {
            amountSpace.append(" ");
        }
        return amountSpace;
    }


    // EFFECTS: create an expense with this information: date, category, amount, comment.
    private void doTrack() {
        LocalDate date = null;

        System.out.print("Do you want to use current date? (Y/N)");
        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("y")) {
            date = LocalDate.now();
        } else if (command.equals("n")) {
            System.out.println("Input your desired date (YYYY/MM/DD):");
            String newDate = input.next();
            int year = Integer.parseInt(newDate.substring(0,4));
            int mon = Integer.parseInt(newDate.substring(5,7));
            int day = Integer.parseInt(newDate.substring(8,10));
            date = LocalDate.of(year, mon, day);
        } else {
            System.out.println("Selection not valid...");
        }

        System.out.println("Input the expense's category:");
        String category = input.next();
        System.out.println("Input the expense's amount:");
        double amount = Double.parseDouble(input.next());
        System.out.println("Input the expense's comment:");
        String comment = input.next();

        exp.addExpense(new Expense(date, category, amount, comment));
    }

    // MODIFIES: this
    // EFFECTS: delete expense(i) in Expense list
    public void doDelete() {
        System.out.println("Input which expense do you wish to delete (begin with 1):");
        int i = Integer.parseInt(input.next());
        exp.deleteExpense(i);

    }

    // EFFECTS: saves the workroom to file
    private void saveExpenses() {
        try {
            jsonWriter.open();
            jsonWriter.write(exp);
            jsonWriter.close();
            System.out.println("Saving expenses to " + JSON_STORE + "...");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadExpenses() {
        try {
            exp = jsonReader.read();
            System.out.println("Loaded Expenses from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
