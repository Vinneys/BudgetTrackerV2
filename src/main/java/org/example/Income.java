package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Income  {
    private double amount;
    private LocalDateTime date;
    private EIncomeCategory category;

    public Income(double amount, String dateString, EIncomeCategory category) {
        this.amount = amount;
        this.category = category;
        this.date = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public EIncomeCategory getCategory() {
        return category;
    }

    public void setCategory(EIncomeCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Income: Amount = " + amount
                + ", Date = " + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
                + ", Category = " + category;
    }
}
