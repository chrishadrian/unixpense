package persistence;

import model.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkExpense(LocalDate date, String category, double amount, String comment, Expense ex) {
        assertEquals(date, ex.getDate());
        assertEquals(category, ex.getCategory());
        assertEquals(amount, ex.getAmount());
        assertEquals(comment, ex.getComment());
    }
}
