package org.example;

public class Transaction {
    protected double amount;
    protected String date;


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

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}