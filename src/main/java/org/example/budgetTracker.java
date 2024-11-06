package org.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class budgetTracker {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    static IncomeStorage incomeStorage = new IncomeStorage();
    static ExpenseStorage expenseStorage = new ExpenseStorage();

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        boolean done = false;
        do {
            System.out.println("1. Add an expense");
            System.out.println("2. Add an income");
            System.out.println("3. Show incomes");
            System.out.println("4. Show expenses");
            System.out.println("5. Delete an income");
            System.out.println("6. Delete an expense");
            System.out.println("7. Show all transactions");
            System.out.println("8. End");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> addExpense();
                case 2 -> addIncome();
                case 3 -> showIncomes();
                case 4 -> showExpenses();
                case 5 -> deleteIncome();
                case 6 -> deleteExpense();
                case 7 -> showAllTransactions();
                case 8 -> done = true;
                default -> System.out.println("Invalid option, please try again.");
            }
        } while (!done);
    }

    static void showIncomes() throws IOException {
        System.out.println("Do you want to see all incomes? (y/n)");
        String anst = scan.nextLine();
        String id = null;
        if (anst.equalsIgnoreCase("y")) {
            incomeStorage.showAllIncomes();
        } else if (anst.equalsIgnoreCase("n")) {
            System.out.println("Type in your ID");
            id = scan.nextLine();
            Income income = incomeStorage.getIncomeByID(id);
            if (income != null) {
                System.out.println("We found your income! " + income);
                System.out.println("Do you want to update your income? (y/n)");
                String update = scan.nextLine();
                if (update.equalsIgnoreCase("y")) {
                    System.out.println("Type in your new amount");
                    double newAmount = scan.nextDouble();
                    scan.nextLine();

                    System.out.println("Type in your new date (dd-MM-yyyy HH:mm)");
                    String newDate = scan.nextLine();

                    System.out.println("Enter the category of the expense (SALARY, BUSINESS, INVESTMENT, OTHER):");
                    String newCategory = scan.nextLine().toUpperCase();
                    EIncomeCategory category;
                    try {
                        category = EIncomeCategory.valueOf(newCategory.toUpperCase());
                        incomeStorage.updateIncome(id, newAmount, newDate, String.valueOf(category));
                        incomeStorage.saveIncomeFile(id, income);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category entered.");
                    }
                } else if (update.equalsIgnoreCase("n")) {
                    System.out.println("OK");
                }


            }
        } else {
            System.out.println("Income with ID " + id + " not found.");
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

            // Parse the category, default to OTHER if invalid
            EExpenseCategory category;
            try {
                category = EExpenseCategory.valueOf(categoryInput);
            } catch (IllegalArgumentException e) {
                category = EExpenseCategory.OTHER;
                System.out.println("Invalid category entered. Defaulting to OTHER category.");
            }

            // Parse the input date
            LocalDateTime date = LocalDateTime.parse(dateInput, formatter);

            String uniqueId = expenseStorage.generateTimeId(date);

            expenseStorage.addExpense(amount, dateInput, categoryInput);

            expenseStorage.saveExpenseFile(uniqueId, new Expense(amount, dateInput, categoryInput));

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

            EIncomeCategory category;
            try {
                category = EIncomeCategory.valueOf(categoryInput);
            } catch (IllegalArgumentException e) {
                category = EIncomeCategory.OTHER;
                System.out.println("Invalid category entered. Defaulting to OTHER category.");
            }

            LocalDateTime date = LocalDateTime.parse(dateInput, formatter);

            String uniqueId = incomeStorage.generateTimeId(date);

            incomeStorage.addIncome(amount, dateInput, categoryInput);

            incomeStorage.saveIncomeFile(uniqueId, new Income(amount, dateInput, categoryInput));
            System.out.println("Income added successfully: Amount: " + amount + ", Date: " + dateInput + ", Category: " + category);

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy HH:mm format.");
        } catch (Exception e) {
            System.out.println("An error occurred while adding the income: " + e.getMessage());
        }
    }

    static void deleteIncome() throws IOException {
        System.out.println("Enter the ID of the income you wish to delete:");
        String id = scan.nextLine();
        incomeStorage.deleteIncome(id);
        incomeStorage.saveIncomeFile(id, null);

    }

    static void deleteExpense() throws IOException {
        System.out.println("Enter the ID of the expense you wish to delete:");
        String id = scan.nextLine();
        expenseStorage.deleteExpense(id);
        expenseStorage.saveExpenseFile(id, null);

    }

    static void showAllTransactions() throws IOException {
        System.out.println("Showing all transactions (incomes and expenses):");

        // Show all incomes
        System.out.println("\n--- Incomes ---");
        incomeStorage.showAllIncomes();

        // Show all expenses
        System.out.println("\n--- Expenses ---");
        expenseStorage.showAllExpenses();
    }

    static void showExpenses() throws IOException {
        System.out.println("Do you want to see all expenses? (y/n)");
        String anst = scan.nextLine();
        if (anst.equalsIgnoreCase("y")) {
            expenseStorage.showAllExpenses();
        } else if (anst.equalsIgnoreCase("n")) {
            System.out.println("Type in your ID");
            String id = scan.nextLine();
            Expense expense = expenseStorage.getExpenseByID(id);
            if (expense != null) {
                System.out.println("We found your expense! " + expense);
                System.out.println("Do you want to update your expense? (y/n)");
                String update = scan.nextLine();
                if (update.equalsIgnoreCase("y")) {
                    System.out.println("Type in your new amount");
                    double newAmount = scan.nextDouble();
                    scan.nextLine();  // Consume the leftover newline

                    System.out.println("Type in your new date (dd-MM-yyyy HH:mm)");
                    String newDate = scan.nextLine();

                    System.out.println("Type in your new category (FOOD, TRANSPORT, ENTERTAINMENT, HEALTH, OTHER)");
                    String newCategory = scan.nextLine();

                    EExpenseCategory category;
                    try {
                        category = EExpenseCategory.valueOf(newCategory.toUpperCase());
                        expenseStorage.updateExpense(id, newAmount, newDate, String.valueOf(category));
                        expenseStorage.saveExpenseFile(id, expense);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category entered.");
                    }
                } else if (update.equalsIgnoreCase("n")) {
                    System.out.println("OK");
                }
            } else {
                System.out.println("Expense not found with the provided ID.");
            }
        }
    }
}
