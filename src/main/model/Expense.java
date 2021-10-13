package model;

import java.time.LocalDate;

public class Expense {
    public LocalDate date;
    public String category;
    public int amount;
    public String comment;

    public Expense(LocalDate date, String category, int amount, String comment) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.comment = comment;
    }
}
