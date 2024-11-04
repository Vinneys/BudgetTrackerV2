package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ExpenseStorage {
    private HashMap<String, Expense> expenseMap = new HashMap<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private String fileName = "src/main/expenses.json";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Add a new expense with a unique key
    public void addExpense(double amount, String dateString, String category) throws IOException {
        try {
            // Parse the date using the defined formatter
            LocalDateTime parsedDate = LocalDateTime.parse(dateString, formatter);
            Expense expense = new Expense(amount, parsedDate.format(formatter), category);

            // Read the file to ensure the latest data is loaded
            readExpenseFile();

            // Generate unique ID based on the parsed date
            String uniqueId = generateTimeId(parsedDate);

            // Add the new expense to the map
            expenseMap.put(uniqueId, expense);

            // Save the updated map to the file
            saveExpenseFile(uniqueId, expense);


        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy HH:mm format.");
        }
    }

    public void readExpenseFile() throws IOException {
        File file = new File(fileName);
        if (file.exists() && file.length() > 0) { // Only read if the file exists and is not empty
            FileReader fr = new FileReader(file);
            Type type = new TypeToken<HashMap<String, Expense>>() {}.getType(); // Specify HashMap explicitly
            expenseMap = gson.fromJson(fr, type);
            fr.close();
            if (expenseMap == null) { // If the file is empty or corrupted
                expenseMap = new HashMap<>(); // Initialize expenseMap
            }
        } else {
            expenseMap = new HashMap<>(); // Initialize map if file doesn't exist
        }
    }

    public void saveExpenseFile(String uniqueID, Expense expense) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        expenseMap.put(uniqueID, expense);
        gson.toJson(expenseMap, fw);
        fw.close();

    }

    // Generate a unique ID based on the time (HHmmss)
    public String generateTimeId(LocalDateTime date) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        return date.format(timeFormatter); // Use formatted time (HHmmss) as ID
    }

    // Update an existing expense entry based on the key//update with json
    public void updateExpense(String key, double newAmount, String newDate, String newCategory) {
        if (expenseMap.containsKey(key)) {
            try {
                // Parse the new date using the specified formatter
                String parsedDate = String.valueOf(LocalDateTime.parse(newDate, formatter));

                // Get the expense object and update its fields
                Expense expense = expenseMap.get(key);
                expense.setAmount(newAmount);
                expense.setDate(parsedDate);
                expense.setCategory(EExpenseCategory.valueOf(newCategory));

                System.out.println("Expense updated successfully for ID: " + key);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy HH:mm format.");
            }
        } else {
            System.out.println("Invalid key. Unable to update expense.");
        }
    }

    public void deleteExpense(String key) throws IOException {
        // Load the current expenses from the file
        readExpenseFile();

        if (expenseMap.containsKey(key)) {
            // Remove the expense from the map
            expenseMap.remove(key);
            System.out.println("Expense with ID " + key + " has been successfully deleted.");

            // Clear the file before saving the updated map
            clearExpenseFile();

            // Save all remaining expenses back to the file
            for (Map.Entry<String, Expense> entry : expenseMap.entrySet()) {
                saveExpenseFile(entry.getKey(), entry.getValue());
            }
        } else {
            System.out.println("Invalid key. No expense found with ID " + key + ".");
        }
    }
    // Method to clear the content of the expenses.json file
    public void clearExpenseFile() throws IOException {
        FileWriter fw = new FileWriter(fileName, false); // Open the file in overwrite mode
        fw.write(""); // Write an empty string to clear the file
        fw.close();
        System.out.println("Expenses file cleared.");
    }




    // Update expense amount for a specific expense entry based on key
    public void setExpenseAmount(String key, double amount) {
        if (expenseMap.containsKey(key)) {
            Expense expense = expenseMap.get(key);
            expense.setAmount(amount); // Assuming the Expense class has a setAmount method
            System.out.println("Expense amount updated successfully for ID: " + key);
        } else {
            System.out.println("Invalid key. Unable to update expense.");
        }
    }

    // Update date for a specific expense entry based on key
    public void setExpenseDate(String key, String date) {
        try {
            if (expenseMap.containsKey(key)) {
                LocalDateTime parsedDate = LocalDateTime.parse(date, formatter);
                Expense expense = expenseMap.get(key);
                expense.setDate(parsedDate.toString()); // Assuming the Expense class has a setDate method that accepts LocalDateTime
                System.out.println("Expense date updated successfully for ID: " + key);
            } else {
                System.out.println("Invalid key. Unable to update date.");
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy HH:mm format.");
        }
    }

    // Show all expenses
    public void showAllExpenses() throws IOException {
        readExpenseFile();
        if (expenseMap.isEmpty()) {
            System.out.println("No Expense found.");
        } else {
            for (Map.Entry<String, Expense> entry : expenseMap.entrySet()) {
                System.out.println("ID: " + entry.getKey() + ", Expense Details: Amount: " + entry.getValue().getAmount() + ", Date: " + entry.getValue().getDate() + ", Category: " + entry.getValue().getCategory());
            }
        }
    }

    // Get expense by key
    public Expense getExpenseByID(String key) {
        return expenseMap.get(key);
    }


}
