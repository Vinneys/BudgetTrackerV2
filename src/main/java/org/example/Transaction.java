package org.example;

public class Transaction {
    private double amount;
    private static String date;


    public Transaction(double amount, String date) {
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

    public static String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}