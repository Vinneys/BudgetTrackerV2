package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Expense extends Transaction {
    private EExpenseCategory category;

    public Expense(double amount, LocalDateTime date, EExpenseCategory category) {
        super(amount, date);  // Call the superclass constructor to set amount and date
        this.category = category;
    }

    public EExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(EExpenseCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expense: Amount = " + getAmount() + ", Category = " + category + ", Date = " + getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
