package model;

import java.util.LinkedList;

// Represents a LinkedList of Expense(s)
public class Expenses {
    private final LinkedList<Expense> expenses;
    private final LinkedList<Expense> archive = new LinkedList<>();


    public Expenses() {
        expenses = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: add an expense to expenses list
    public void addExpense(Expense e) {
        expenses.add(e);
    }

    // REQUIRES: list must not be empty
    // MODIFIES: this
    // EFFECTS: delete the latest expense in the list
    public Expense deleteLastExpense() {
        return expenses.removeLast();
    }

    // EFFECTS: return the sum of the expenses amount
    public int sumExpenses() {
        int sum = 0;
        for (Expense next : expenses) {
            sum += next.getAmount();
        }
        return sum;
    }

    // REQUIRES: expenses list must not be empty
    // EFFECTS: get the first expense of expenses
    public Expense getExpense(int i) {
        return expenses.get(i);
    }

    // MODIFIES: this
    // EFFECTS: duplicate current Expenses to a new Expenses, reset current Expenses
    public void archiveExpenses() {
        archive.addAll(expenses);
        expenses.clear();
    }

    // EFFECTS: return expenses size
    public int length() {
        return expenses.size();
    }
}