package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// JSON files were cited from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Expenses exp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyExpenses() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyExpenses.json");
        try {
            Expenses exp = reader.read();
            assertEquals(0, exp.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralExpenses() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralExpenses.json");
        try {
            Expenses exp = reader.read();
            List<Expense> expenses = exp.getExpenses();
            assertEquals(2, expenses.size());
            checkExpense(LocalDate.now(), "Groceries", 10, "", expenses.get(0));
            checkExpense(LocalDate.now(), "Personal", 32.2, "", expenses.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}