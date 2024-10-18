package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class budgetTracker {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    static IncomeStorage incomeStorage = new IncomeStorage();
    static ExpenseStorage expenseStorage = new ExpenseStorage(); // Add an expense storage




    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        //incomeStorage.loadFromFile();
        /*
                System.out.println("What is your first name?");
                String firstName = scan.nextLine();
                System.out.println("What is your last name?");
                String lastName = scan.nextLine();
                User user = new User(firstName, lastName);

        */
        boolean done = false;
        do {
            //System.out.println("Welcome " + user + " to your own Budget Tracker!");
            System.out.println("1. Add an expense");
            System.out.println("2. Add an income");
            System.out.println("3. Show all incomes");
            System.out.println("4. Show all expenses");
            System.out.println("5. End");

            int choice = scan.nextInt();
            scan.nextLine();

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
                case 4 -> {
                    showExpenses();
                }
                case 5 -> {
                    done = true;  // This will properly exit the loop
                }
                default -> {
                    System.out.println("Invalid option, please try again.");
                }
            }
        } while (!done);  // Continue looping until the user chooses to end

    }

    static void showIncomes() {
        System.out.println("Do you want to see all incomes? (y/n)");
        String anst = scan.nextLine();
        if (anst.equalsIgnoreCase("y")) {
            for (Income income : incomeStorage.getIncomes()) {
                System.out.println(income);
            }
        }
        else if (anst.equalsIgnoreCase("n")) {
            System.out.println("Type in your ID");
            String id = scan.nextLine();
            incomeStorage.getIncomeByID(id);
            System.out.println("We found your income! "+ incomeStorage.getIncomeByID(id));
        }
    }


    static void addExpense() {
        try {
            System.out.println("Type in the amount of the expense:");
            double amount = scan.nextDouble();
            scan.nextLine();

            System.out.println("Enter the date of the transaction (format: YYYY-MM-DD):");
            String dateInput = scan.nextLine();

            LocalDateTime date = LocalDateTime.parse(dateInput, formatter);
            String formattedDate = date.format(formatter);// Parse string to LocalDate

            System.out.println("Expense added successfully: Amount: " + amount + ", Date: " + formattedDate);


        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
        } catch (Exception e) {
            System.out.println("An error occurred while adding the expense: " + e.getMessage());
        }
    }

    static void addIncome() {
        try {
            System.out.println("Type in the amount of the income:");
            double amount = scan.nextDouble();
            scan.nextLine();

            System.out.println("Enter the date of the transaction (format: dd-MM-yyyy HH:mm):");
            String dateInput = scan.nextLine();

            // Parse the input date using the formatter
            LocalDateTime date = LocalDateTime.parse(dateInput, formatter);

            // Convert the date back to a formatted string before storing or displaying
            String formattedDate = date.format(formatter);

            System.out.println("Income added successfully: Amount: " + amount + ", Date: " + formattedDate);
            incomeStorage.addIncome(new Income(amount, formattedDate));  // Store the formatted date string

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy HH:mm format.");
        } catch (Exception e) {
            System.out.println("An error occurred while adding the income: " + e.getMessage());
        }
    }


    static void showExpenses() {
        ExpenseStorage expenseStorage = new ExpenseStorage();
        for (Expense expense : ExpenseStorage.getExpenses()) {
            System.out.println(expense);
        }
    }
}
//https://www.javatpoint.com/java-localdatetime