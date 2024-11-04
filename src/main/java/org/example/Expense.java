package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Expense extends Transaction {
    private String category;
    private EExpenseCategory ecategory;



    // Constructor to initialize an Expense object
    public Expense(double amount, String dateString, String category) {
        super(amount, dateString);

        switch (category) {
            case "FOOD":
                ecategory = EExpenseCategory.valueOf(this.category = String.valueOf(EExpenseCategory.FOOD));
                break;
            case "ENTERTAINMENT":
                ecategory = EExpenseCategory.valueOf(this.category = String.valueOf(EExpenseCategory.ENTERTAINMENT));
                break;
            case "TRANSPORT":
                ecategory = EExpenseCategory.valueOf(this.category = String.valueOf(EExpenseCategory.TRANSPORT));
                break;
            case "OTHER":
                ecategory = EExpenseCategory.valueOf(this.category = String.valueOf(EExpenseCategory.OTHER));
                break;
        }
    }

    public EExpenseCategory getCategory() {
        return ecategory;
    }

    public void setCategory(EExpenseCategory category) {
        this.ecategory = category;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "category='" + category + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}