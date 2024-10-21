package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Expense {
    private double amount;
    private EExpenseCategory category;
    private LocalDateTime date;

    public Expense( double amount, String dateString) {
        this.amount = amount;
        this.date = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public EExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(EExpenseCategory category) {
        this.category = category;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
