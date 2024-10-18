package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IncomeStorage {
    private HashMap<String, Income> incomeMap = new HashMap<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    // Add a new income with a unique key
    public void addIncome(Income income) {
        String uniqueId = generateTimeId(income.getDate());  // Generate a unique key
        incomeMap.put(uniqueId, income);
        System.out.println("Income added with ID: " + uniqueId);
    }

    // Update income amount for a specific income entry based on key
    private String generateTimeId(LocalDateTime date) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        return date.format(timeFormatter); // Use formatted time (HHmmss) as ID
    }

    // Update income amount for a specific income entry based on key
    public void setIncome(String key, double amount) {
        if (incomeMap.containsKey(key)) {
            Income income = incomeMap.get(key);
            income.setAmount(amount); // Assuming the Income class has a setAmount method
            System.out.println("Income amount updated successfully for ID: " + key);
        } else {
            System.out.println("Invalid key. Unable to update income.");
        }
    }

    // Update date for a specific income entry based on key
    public void setDate(String key, String date) {
        try {
            if (incomeMap.containsKey(key)) {
                LocalDateTime parsedDate = LocalDateTime.parse(date, formatter);
                Income income = incomeMap.get(key);
                income.setDate(parsedDate); // Assuming the Income class has a setDate method that accepts LocalDateTime
                System.out.println("Income date updated successfully for ID: " + key);
            } else {
                System.out.println("Invalid key. Unable to update date.");
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date in yyyy/MM/dd HH:mm:ss format.");
        }
    }

    // Show all incomes
    public void showAllIncomes() {
        if (incomeMap.isEmpty()) {
            System.out.println("No incomes recorded.");
        } else {
            for (Map.Entry<String, Income> entry : incomeMap.entrySet()) {
                System.out.println("ID: " + entry.getKey() + ", " + entry.getValue());
            }
        }
    }

    // Get income by key
    public Income getIncomeByID(String key) {
        return incomeMap.get(key);
    }

    // Get all incomes as a list
    public ArrayList<Income> getIncomes() {
        return new ArrayList<>(incomeMap.values());  // Convert HashMap values to an ArrayList
    }
}