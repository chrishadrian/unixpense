package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

// JSON files were cited from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Expenses read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExpenses(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Expenses parseExpenses(JSONObject jsonObject) {
        Expenses exp = new Expenses();
        addExpenses(exp, jsonObject);
        return exp;
    }

    // MODIFIES: exp
    // EFFECTS: parses expenses from JSON object and adds them to workroom
    private void addExpenses(Expenses exp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpense(exp, nextExpense);
        }
    }

    // MODIFIES: exp
    // EFFECTS: parses expense from JSON object and adds it to expenses
    private void addExpense(Expenses exp, JSONObject jsonObject) {
        String dateStr = jsonObject.getString("date");
        LocalDate date = LocalDate.parse(dateStr);
        String category = jsonObject.getString("category");
        String amountStr = jsonObject.getString("amount");
        double amount = Double.parseDouble(amountStr);
        String comment = jsonObject.getString("comment");
        Expense ex = new Expense(date, category, amount, comment);
        exp.addExpense(ex);
    }
}
