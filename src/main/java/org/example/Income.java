package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Income extends Transaction {
    private EIncomeCategory category;

    public Income(double amount, LocalDateTime date, EIncomeCategory category) {
        super(amount, date);  // Call the superclass constructor to set amount and date
        this.category = category;
    }

    public EIncomeCategory getCategory() {
        return category;
    }

    public void setCategory(EIncomeCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Income: Amount = " + getAmount() + ", Category = " + category + ", Date = " + getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
