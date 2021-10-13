package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpensesTest {
    private Expenses exp;
    private Expense ex, ex2;
    @BeforeEach
    public void runBefore() {
        exp = new Expenses();

        ex = new Expense(LocalDate.now(), "Groceries", 32, "");
        ex2 = new Expense(LocalDate.now(), "Personal", 32, "");
        exp.addExpense(ex);
        exp.addExpense(ex);
        exp.addExpense(ex2);

    }

    @Test
    public void addExpenseTest() {
        assertEquals(3, exp.length());
    }

    @Test
    public void deleteLastExpenseTest() {
        assertEquals(ex2, exp.deleteLastExpense());
        assertEquals(2, exp.length());
    }

    @Test
    public void sumExpensesTest() {
        assertEquals(96, exp.sumExpenses());

        exp.deleteLastExpense();
        assertEquals(64, exp.sumExpenses());
    }

    @Test
    public void getExpenseTest() {
        assertEquals(ex, exp.getExpense(0));
        assertEquals(ex2, exp.getExpense(2));
    }
}
