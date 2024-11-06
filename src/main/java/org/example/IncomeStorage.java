package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class IncomeStorage {
    private HashMap<String, Income> incomeMap = new HashMap<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private String fileName = "src/main/incomes.json";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Add a new income with a unique key
    public void addIncome(double amount, String dateString, String category) throws IOException {
        try {
            // Parse the date using the defined formatter
            LocalDateTime parsedDate = LocalDateTime.parse(dateString, formatter);
            Income income = new Income(amount, parsedDate.format(formatter), category);

            // Read the file to ensure the latest data is loaded
            readIncomeFile();

            // Generate unique ID based on the parsed date
            String uniqueId = generateTimeId(parsedDate);

            // Add the new income to the map
            incomeMap.put(uniqueId, income);

            // Save the updated map to the file
            saveIncomeFile(uniqueId, income);


        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy HH:mm format.");
        }
    }

    public void readIncomeFile() throws IOException {
        File file = new File(fileName);
        if (file.exists() && file.length() > 0) { // Only read if the file exists and is not empty
            FileReader fr = new FileReader(file);
            Type type = new TypeToken<HashMap<String, Income>>() {}.getType(); // Specify HashMap explicitly
            incomeMap = gson.fromJson(fr, type);
            fr.close();
            if (incomeMap == null) { // If the file is empty or corrupted
                incomeMap = new HashMap<>(); // Initialize incomeMap
            }
        } else {
            incomeMap = new HashMap<>(); // Initialize map if file doesn't exist
        }
    }


    public void saveIncomeFile(String uniqueID, Income income) throws IOException {

        FileWriter fw = new FileWriter(fileName);
        incomeMap.put(uniqueID, income);
        gson.toJson(incomeMap, fw);
        fw.close();



    }

    // Update income amount for a specific income entry based on key
    public String generateTimeId(LocalDateTime date) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        return date.format(timeFormatter); // Use formatted time (HHmmss) as ID
    }

    // Update an existing income entry based on the key
    public void updateIncome(String key, double newAmount, String newDate, String newCategory) {
        if (incomeMap.containsKey(key)) {
            try {
                String parsedDate = String.valueOf(LocalDateTime.parse(newDate, formatter));

                Income income = incomeMap.get(key);
                income.setAmount(newAmount);
                income.setDate(parsedDate);

                System.out.println("Income updated successfully for ID: " + key);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy HH:mm format.");
            }
        } else {
            System.out.println("Invalid key. Unable to update income.");
        }
    }

    public void deleteIncome(String key) throws IOException {
        if (incomeMap.containsKey(key)) {
            incomeMap.remove(key);
            System.out.println("Income with ID " + key + " has been successfully deleted.");
        } else {
            System.out.println("Invalid key. No income found with ID " + key + ".");
        }

    }


    // Update income amount for a specific income entry based on key
    public void setIncome(String key, double amount) {
        if (incomeMap.containsKey(key)) {
            Income income = incomeMap.get(key);
            income.setAmount(amount);
            System.out.println("Income amount updated successfully for ID: " + key);
        } else {
            System.out.println("Invalid key. Unable to update income.");
        }
    }

    // Update date for a specific income entry based on key
   /* public void setDate(String key, String date) {
        try {
            if (incomeMap.containsKey(key)) {
                LocalDateTime parsedDate = LocalDateTime.parse(date, formatter);
                Income income = incomeMap.get(key);
                income.setDate(parsedDate);
                System.out.println("Income date updated successfully for ID: " + key);
            } else {
                System.out.println("Invalid key. Unable to update date.");
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date in yyyy/MM/dd HH:mm:ss format.");
        }
    }
*/
    public void showAllIncomes() throws IOException {
        readIncomeFile();
        if (incomeMap.isEmpty()) {
            System.out.println("No Income found.");
        } else {
            for (Map.Entry<String, Income> entry : incomeMap.entrySet()) {
                System.out.println("ID: " + entry.getKey() + ", Income Details: Amount: " + entry.getValue().getAmount() + ", Date: " + entry.getValue().getDate() + ", Category: " + entry.getValue().getCategory());
            }
        }
    }


    public Income getIncomeByID(String key) {
        return incomeMap.get(key);
    }


}