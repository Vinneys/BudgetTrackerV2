package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Income {
    private double amount;
    private LocalDateTime date;

    public Income(double amount, String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.amount = amount;
        this.date = LocalDateTime.parse(dateString, formatter);
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

    @Override
    public String toString() {
        return "Income: Amount = " + amount + ", Date = " + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
