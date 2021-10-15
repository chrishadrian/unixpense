package model;

import java.time.LocalDate;

// Represents an expense that has this information: date, category, amount, comment.
public class Expense {
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
}
