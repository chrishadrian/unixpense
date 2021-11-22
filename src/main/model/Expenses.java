package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// Represents a LinkedList of Expense(s)
public class Expenses implements Writable {
    private final LinkedList<Expense> expenses;

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
    public Expense deleteExpense(int i) {
        return expenses.remove(i);
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

    // EFFECTS: return expenses size
    public int length() {
        return expenses.size();
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

    // EFFECTS: returns an unmodifiable list of expenses in this workroom
    public List<Expense> getExpenses() {
        return Collections.unmodifiableList(expenses);
    }
}
