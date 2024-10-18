package org.example;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class IncomeStorage {
    private ArrayList<Income> incomes = new ArrayList<>();



    // Lägger till en inkomst
    public void addIncome(Income income) {
        incomes.add(income);
        saveToFile();
    }

    // Spara inkomster till JSON-fil
    public void saveToFile() {
        try (FileWriter writer = new FileWriter("incomes.json")) {
            Gson gson = new Gson();
            gson.toJson(incomes, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Läs in inkomster från JSON-fil
    public void loadFromFile() {
        try (FileReader reader = new FileReader("incomes.json")) {
            Gson gson = new Gson();
            Type incomeListType = new TypeToken<List<Income>>(){}.getType();
            incomes = gson.fromJson(reader, incomeListType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Visa alla inkomster
    public ArrayList<Income> getIncomes() {
        return incomes;
    }

    // Uppdatera och ta bort funktioner kan implementeras här
}
