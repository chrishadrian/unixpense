package model;

import java.time.LocalDate;

public class Expense {
    private LocalDate date;
    private String category;
    private int amount;
    private String comment;

    public Expense(LocalDate date, String category, int amount, String comment) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getComment() {
        return comment;
    }
}
