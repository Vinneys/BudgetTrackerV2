package org.example;

import java.time.LocalDate;

public class Income extends Transaction{



    public Income(double amount, String date) {
        super(amount, LocalDate.parse(date));
    }



}
