package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class budgetTracker {
    static IncomeStorage incomeStorage = new IncomeStorage();


    static Scanner scan = new Scanner(System.in);  // General scanner for input

    public static void main(String[] args) {
        incomeStorage.loadFromFile();

        boolean done = false;
        do {


            System.out.println("Welcome to your own Budget Tracker!");
            System.out.println("1. Add an expense");
            System.out.println("2. Add an income");
            System.out.println("3. Show all expenses");
            System.out.println("4. Show all incomes");
            System.out.println("5. End");


            int choice = scan.nextInt();  // Get user's choice
            scan.nextLine();  // Consume leftover newline

            switch (choice) {
                case 1 -> {
                    addExpense();
                }
                case 2 -> {
                    addIncome();

                }
                case 3 -> {
                    showIncomes();

                }
                case 5 -> {
                    System.out.println("Program terminated.");
                }
                case 6 -> done = true;
                default -> {
                    System.out.println("Invalid option, please try again.");
                }
            }
        } while (done);
    }
dfdgawdw
    static void showIncomes() {
   // Transaction transaction = new Transaction(0.0);
        //System.out.println(transaction.getAmount() + " " + transaction.getDate());


    }

    static void addExpense() {
        try {
            System.out.println("Type in the amount of the expense:");
            double amount = scan.nextDouble();
            scan.nextLine();

            System.out.println("Enter the date of the transaction (format: YYYY-MM-DD):");
            String dateInput = scan.nextLine();

            LocalDate date = LocalDate.parse(dateInput);  // Parse string to LocalDate

            System.out.println("Expense added successfully: Amount: " + amount + ", Date: " + date);


        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
        } catch (Exception e) {
            System.out.println("An error occurred while adding the expense: " + e.getMessage());
        }
    }

    static void addIncome() {
        try {


            System.out.println("Type in the amount of the Income:");
            double amount = scan.nextDouble();
            scan.nextLine();

            System.out.println("Enter the date of the transaction (format: YYYYMMDD):");
            String dateInput = scan.nextLine();

            // Parse string to LocalDate

            System.out.println("Income added successfully: Amount: " + amount + ", Date: " + dateInput);

            incomeStorage.addIncome(new Income(amount, dateInput));
            incomeStorage.saveToFile();


        } catch (Exception e) {
            System.out.println("An error occurred while adding the income: " + e.getMessage());
        }
    }
}