package org.example;
import java.time.LocalDate;

public class Expense  extends  Transaction{
    private EExpenseCategory category;


    public Expense(double amount, LocalDate date) {
        super(amount, date);
    }
    public EExpenseCategory getCategory(){
        return category;
    }
    public void setCategory(EExpenseCategory category) {
        this.category = category;
    }
}
