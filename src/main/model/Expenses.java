package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.LinkedList;

// Represents a LinkedList of Expense(s)
public class Expenses implements Writable {
    private final LinkedList<Expense> expenses;
    private final LinkedList<Expense> archive;


    public Expenses() {
        expenses = new LinkedList<>();
        archive = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: add an expense to expenses list
    public void addExpense(Expense e) {
        expenses.add(e);
    }

    // REQUIRES: list must not be empty
    // MODIFIES: this
    // EFFECTS: delete the latest expense in the list
    public Expense deleteExpense(int i) {
        return expenses.remove(i - 1);
    }

    // EFFECTS: return the sum of the expenses amount
    public double sumExpenses() {
        double sum = 0;
        for (Expense next : expenses) {
            sum += next.getAmount();
        }
        return sum;
    }

    // MODIFIES: THIS
    // EFFECTS: Sort the expenses in the list based on date created (from oldest to newest)
    public void sortExpensesDate() {
        expenses.sort((x, y) -> x.getDate().compareTo(y.getDate()));
    }

    // REQUIRES: expenses list must not be empty
    // EFFECTS: get the first expense of expenses
    public Expense getExpense(int i) {
        return expenses.get(i);
    }

    public Expense getArchive(int i) {
        return archive.get(i);
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

    // EFFECTS: return archive's size
    public int archiveLength() {
        return archive.size();
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("expenses", expensesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense ex : expenses) {
            jsonArray.put(ex.toJson());
        }

        return jsonArray;
    }
}
