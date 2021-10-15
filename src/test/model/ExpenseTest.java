package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseTest {
    Expense ex;

    @BeforeEach
    public void runBefore() {
        ex = new Expense(LocalDate.now(), "Groceries", 31.3, "Save-On");
    }

    @Test
    public void getDateTest() {
        assertEquals(LocalDate.now(), ex.getDate());
    }

    @Test
    public void getCategoryTest() {
        assertEquals("Groceries", ex.getCategory());
    }

    @Test
    public void getAmountTest() {
        assertEquals(31.3, ex.getAmount());
    }

    @Test
    public void getCommentTest() {
        assertEquals("Save-On", ex.getComment());
    }

}
