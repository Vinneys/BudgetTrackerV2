package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ExpenseStorage {
    private final HashMap<String, Expense> expenseMap = new HashMap<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    // Add a new expense with a unique key
    public void addExpense(Expense expense) {
        String uniqueId = generateTimeId(expense.getDate());  // Generate a unique key
        expenseMap.put(uniqueId, expense);
        System.out.println("Expense added with ID: " + uniqueId);
    }

    // Generate a unique ID based on the time
    private String generateTimeId(LocalDateTime date) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        return date.format(timeFormatter); // Use formatted time (HHmmss) as ID
    }

    // Update an existing expense entry based on the key
    public void updateExpense(String key, double newAmount, String newDate) {
        if (expenseMap.containsKey(key)) {
            try {
                // Parse the new date using the specified formatter
                LocalDateTime parsedDate = LocalDateTime.parse(newDate, formatter);

                // Get the expense object and update its fields
                Expense expense = expenseMap.get(key);
                expense.setAmount(newAmount);
                expense.setDate(parsedDate);

                System.out.println("Expense updated successfully for ID: " + key);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy HH:mm format.");
            }
        } else {
            System.out.println("Invalid key. Unable to update expense.");
        }
    }

    // Delete an existing expense entry based on the key
    public void deleteExpense(String key) {
        if (expenseMap.containsKey(key)) {
            expenseMap.remove(key);
            System.out.println("Expense with ID " + key + " has been successfully deleted.");
        } else {
            System.out.println("Invalid key. No expense found with ID " + key + ".");
        }
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
                expense.setDate(parsedDate); // Assuming the Expense class has a setDate method that accepts LocalDateTime
                System.out.println("Expense date updated successfully for ID: " + key);
            } else {
                System.out.println("Invalid key. Unable to update date.");
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy HH:mm format.");
        }
    }

    // Show all expenses
    public void showAllExpenses() {
        if (expenseMap.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            for (Map.Entry<String, Expense> entry : expenseMap.entrySet()) {
                System.out.println("ID: " + entry.getKey() + ", Expense Details: Amount: " + entry.getValue().getAmount() + ", Date: " + entry.getValue().getDate().format(formatter) + ", Category: " + entry.getValue().getCategory());
            }
        }
    }

    // Get expense by key
    public Expense getExpenseByID(String key) {
        return expenseMap.get(key);
    }
}
