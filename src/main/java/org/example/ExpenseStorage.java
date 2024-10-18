package org.example;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
public class ExpenseStorage {
    private ArrayList<Expense> expenses = new ArrayList<>();

    // Lägger till en utgift
    public void addExpense(Expense expense) {
        expenses.add(expense);
        saveToFile();
    }

    // Spara utgifter till JSON-fil
    private void saveToFile() {
        try (FileWriter writer = new FileWriter("expenses.json")) {
            Gson gson = new Gson();
            gson.toJson(expenses, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Läs in utgifter från JSON-fil
    public void loadFromFile() {
        try (FileReader reader = new FileReader("expenses.json")) {
            Gson gson = new Gson();
            Type expenseListType = new TypeToken<List<Expense>>() {
            }.getType();
            expenses = gson.fromJson(reader, expenseListType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Visa alla utgifter
    public ArrayList<Expense> getExpenses() {
        return expenses;
    }
}
