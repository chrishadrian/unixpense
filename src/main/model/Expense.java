package model;


import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;

// Represents an expense that has this information: date, category, amount, comment.
public class Expense implements Writable {
    private LocalDate date;
    private String category;
    private double amount;
    private String comment;

    public Expense(LocalDate date, String category, double amount, String comment) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date);
        json.put("category", category);
        json.put("amount", Double.toString(amount));
        json.put("comment", comment);
        return json;
    }
}
