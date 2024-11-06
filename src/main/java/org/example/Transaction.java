package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private double amount;
    private LocalDateTime date;


    public Transaction(double amount, LocalDateTime date) {
        this.amount = amount;
        this.date = date;
    }


//hej
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
}