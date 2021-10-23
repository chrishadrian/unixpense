package ui;

import model.Expense;
import model.Expenses;

import java.time.LocalDate;
import java.util.Scanner;

// Unixpense application
public class Unixpense {
    private Expenses exp;
    private Scanner input;

    // EFFECTS: runs the Unixpense application
    public Unixpense() {
        runUnixpense();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runUnixpense() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
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
            case "a":
                doViewArchive();
                break;
            case "s":
                doArchive();
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
        System.out.println("\ta -> to view your archives");
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
        System.out.println("\ts -> to store your expenses to archive");
        System.out.println("\td -> to delete your i-th expense");
        System.out.println("\tb -> to go back to home menu");

        String command = input.next();
        if (command.equals("b")) {
            System.out.println("Returning to menu...");
        } else {
            processCommand(command);
        }
    }

    // EFFECTS: display the archive list
    private void doViewArchive() {
        exp.sortExpensesDate();
        String space = "        ";
        System.out.println("Date" + space + space + "Category" +  space + "  Amount" + space + "Comment");
        double sum = 0;
        for (int i = 0; i < exp.archiveLength(); i++) {
            Expense ex1 = exp.getArchive(i);
            sum = sum + ex1.getAmount();

            StringBuilder categorySpace = categorySpace(ex1.getCategory().length());
            StringBuilder amountSpace   = amountSpace(String.valueOf(Math.floor((ex1.getAmount()))).length());

            System.out.println(ex1.getDate() + space + "  " + ex1.getCategory() + categorySpace + space
                    + ex1.getAmount() + amountSpace + space +  ex1.getComment());
        }
        System.out.println("Sum of the month: " + "                    " + sum);
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
    // EFFECTS: store current list of expenses to archive
    public void doArchive() {
        System.out.println("Archiving expenses...");
        exp.archiveExpenses();
    }

    // MODIFIES: this
    // EFFECTS: delete expense(i) in Expense list
    public void doDelete() {
        System.out.println("Input which expense do you wish to delete (begin with 1):");
        int i = Integer.parseInt(input.next());
        exp.deleteExpense(i);

    }
}
