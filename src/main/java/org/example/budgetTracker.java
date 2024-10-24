package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class budgetTracker {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    static IncomeStorage incomeStorage = new IncomeStorage();
    static ExpenseStorage expenseStorage = new ExpenseStorage();


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
            System.out.println("3. Show incomes");
            System.out.println("4. Show expenses");
            System.out.println("5. Delete an income");
            System.out.println("6. End");
            System.out.println("7. Delete expense");
            System.out.println("8. Show all transactions");


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
                    deleteIncome();
                }
                case 6 -> {
                    done = true;
                }
                case 7 ->{
                    deleteExpense();
                }
                case 8 -> {
                    showAllTransactions();
                }
                default -> {
                    System.out.println("Invalid option, please try again.");
                }
            }
        } while (!done);

    }

    static void showIncomes() {
        System.out.println("Do you want to see all incomes? (y/n)");
        String anst = scan.nextLine();
        if (anst.equalsIgnoreCase("y")) {
            incomeStorage.showAllIncomes();
        } else if (anst.equalsIgnoreCase("n")) {
            System.out.println("Type in your ID");
            String id = scan.nextLine();
            Income income = incomeStorage.getIncomeByID(id);
            if (income != null) {
                System.out.println("We found your income! " + income);
                System.out.println("Do you want to update your income? (y/n)");
                String update = scan.nextLine();
                if (update.equalsIgnoreCase("y")) {
                    System.out.println("Type in your new amount");
                    double newAmount = scan.nextDouble();
                    scan.nextLine();  // Consume newline

                    System.out.println("Type in your new date (dd-MM-yyyy HH:mm)");
                    String newDate = scan.nextLine();

                    incomeStorage.updateIncome(id, newAmount, newDate);
                }
            } else {
                System.out.println("Income with ID " + id + " not found.");
            }
        }
    }


    static void addExpense() {
        try {
            System.out.println("Type in the amount of the expense:");
            double amount = scan.nextDouble();
            scan.nextLine();

            System.out.println("Enter the date of the transaction (format: dd-MM-yyyy HH:mm):");
            String dateInput = scan.nextLine();

            System.out.println("Enter the category of the expense (FOOD, TRANSPORT, ENTERTAINMENT, HEALTH, OTHER):");
            String categoryInput = scan.nextLine().toUpperCase();

            // Attempt to parse the category; if invalid, assign OTHER
            EExpenseCategory category;
            try {
                category = EExpenseCategory.valueOf(categoryInput);
            } catch (IllegalArgumentException e) {
                category = EExpenseCategory.OTHER; // Default to OTHER if the input is invalid
                System.out.println("Invalid category entered. Defaulting to OTHER category.");
            }

            // Parse the input date using the formatter
            LocalDateTime date = LocalDateTime.parse(dateInput, formatter);

            // Create the expense object
            Expense expense = new Expense(amount, date, category);
            expenseStorage.addExpense(expense);

            System.out.println("Expense added successfully: Amount: " + amount + ", Date: " + dateInput + ", Category: " + category);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy HH:mm format.");
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

            System.out.println("Enter the category of the income (SALARY, BUSINESS, INVESTMENT, OTHER):");
            String categoryInput = scan.nextLine().toUpperCase();

            // Attempt to parse the category; if invalid, assign OTHER
            EIncomeCategory category;
            try {
                category = EIncomeCategory.valueOf(categoryInput);
            } catch (IllegalArgumentException e) {
                category = EIncomeCategory.OTHER; // Default to OTHER if the input is invalid
                System.out.println("Invalid category entered. Defaulting to OTHER category.");
            }

            // Parse the input date using the formatter
            LocalDateTime date = LocalDateTime.parse(dateInput, formatter);

            Income income = new Income(amount, date, category);
            incomeStorage.addIncome(income);

            System.out.println("Income added successfully: Amount: " + amount + ", Date: " + dateInput + ", Category: " + category);

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy HH:mm format.");
        } catch (Exception e) {
            System.out.println("An error occurred while adding the income: " + e.getMessage());
        }
    }


    static void deleteIncome() {
        System.out.println("Enter the ID of the income you wish to delete:");
        String id = scan.nextLine();
        incomeStorage.deleteIncome(id);
    }


    public static void showExpenses() {
        System.out.println("Do you want to see all expenses? (y/n)");
        String anst = scan.nextLine();
        if (anst.equalsIgnoreCase("y")) {
            expenseStorage.showAllExpenses();
        } else if (anst.equalsIgnoreCase("n")) {
            System.out.println("Type in your ID");
            String id = scan.nextLine();
            Expense expense = expenseStorage.getExpenseByID(id);
            if (expense != null) {
                System.out.println("We found your expense! Amount: " + expense.getAmount() + ", Date: " + expense.getDate().format(formatter) + ", Category: " + expense.getCategory());
                System.out.println("Do you want to update your expense? (y/n)");
                String update = scan.nextLine();
                if (update.equalsIgnoreCase("y")) {
                    System.out.println("Type in your new amount");
                    double newAmount = scan.nextDouble();
                    scan.nextLine();

                    System.out.println("Type in your new date (dd-MM-yyyy HH:mm)");
                    String newDate = scan.nextLine();

                    expenseStorage.updateExpense(id, newAmount, newDate);
                }
            } else {
                System.out.println("Expense with ID " + id + " not found.");
            }
        }
    }
    public static void deleteExpense() {
        System.out.println("Enter the ID of the expense you wish to delete:");
        String id = scan.nextLine();
        expenseStorage.deleteExpense(id);
    }
    public static void showAllTransactions() {
        System.out.println("Showing all transactions:");

        System.out.println("\n--- Expenses ---");
        expenseStorage.showAllExpenses();

        System.out.println("\n--- Incomes ---");
        incomeStorage.showAllIncomes();
    }

}
//https://www.javatpoint.com/java-localdatetime