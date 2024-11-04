package org.example;

import java.time.LocalDateTime;


public class Income extends Transaction{
    private String category;
    private EIncomeCategory ecategory;



    public Income(double amount, String dateString, String category) {
        super(amount, dateString);

        switch (category){
            case "SALARY":
                ecategory = EIncomeCategory.valueOf(this.category = String.valueOf(EIncomeCategory.SALARY));
                break;
            case "INVESTMENT":
                ecategory = EIncomeCategory.valueOf(this.category = String.valueOf(EIncomeCategory.INVESTMENT));
                break;

            case "BUSINESS":
                ecategory = EIncomeCategory.valueOf(this.category = String.valueOf(EIncomeCategory.BUSINESS));
            default:
                ecategory = EIncomeCategory.valueOf(this.category = String.valueOf(EIncomeCategory.OTHER));
                break;
        }

    }

    public EIncomeCategory getCategory() {
        return ecategory;
    }

    public void setCategory(EIncomeCategory category) {
        this.ecategory = category;
    }

    @Override
    public String toString() {
        return "Income{" +
                "category='" + category + '\'' +
                ", amount=" + amount +
                ", date='" + date.formatted("dd-MM-yyyy HH:mm") + '\'' +
                '}';
    }
}