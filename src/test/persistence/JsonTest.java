package persistence;

import model.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

// JSON files were cited from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void checkExpense(LocalDate date, String category, double amount, String comment, Expense ex) {
        assertEquals(date, ex.getDate());
        assertEquals(category, ex.getCategory());
        assertEquals(amount, ex.getAmount());
        assertEquals(comment, ex.getComment());
    }
}
