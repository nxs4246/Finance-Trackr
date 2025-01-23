package com.example.financetracker;

import com.example.financetracker.model.Transaction;
import com.example.financetracker.model.User;
import com.example.financetracker.service.TransactionService;
import com.example.financetracker.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class TestServiceLayer {

    @Bean
    CommandLineRunner run(UserService userService, TransactionService transactionService) {
        return args -> {
            // Test UserService
            System.out.println("Testing UserService...");

            // 1. Create and save a user using .builder()
            User user = User.builder()
                    .username("testuser")
                    .firstName("John")
                    .lastName("Doe")
                    .email("testuser@example.com")
                    .password("password123")
                    .build();

            user = userService.saveUser(user);
            System.out.println("Saved user: " + user);

            // 2. Find user by username
            userService.getUserByUsername("testuser").ifPresentOrElse(
                    foundUser -> System.out.println("User found: " + foundUser),
                    () -> System.out.println("User not found!")
            );
            // Find user by email
            userService.getUserByEmail("testuser@example.com").ifPresentOrElse(
                    foundUser -> System.out.println("User found: " + foundUser),
                    () -> System.out.println("User not found!")
            );

            // Test TransactionService
            System.out.println("\nTesting TransactionService...");

            // 3. Add transactions for the user using .builder()
            Transaction transaction1 = Transaction.builder()
                    .type("Expense")
                    .category("Food")
                    .amount(50.0)
                    .date(LocalDate.now())
                    .description("groceries for this week")
                    .user(user) // Set the user explicitly
                    .build();

            Transaction transaction2 = Transaction.builder()
                    .type("Income")
                    .category("Salary")
                    .amount(1000.0)
                    .date(LocalDate.now())
                    .description("monthly salary")
                    .user(user) // Set the user explicitly
                    .build();

            transactionService.saveTransaction(transaction1);
            transactionService.saveTransaction(transaction2);

            // 4. Fetch transactions for the user
            List<Transaction> transactions = transactionService.getTransactionsByUserId(user.getId());
            System.out.println("Transactions for user: " + transactions);

            // 5. Fetch transactions by category
            List<Transaction> foodTransactions = transactionService.getTransactionsByCategory(user.getId(), "Food");
            System.out.println("Food transactions: " + foodTransactions);

            // Testing Delete
            if(!transactions.isEmpty()) {
                transactionService.deleteTransaction(transaction2.getId());
                System.out.println("Deleted food transaction ...");
            } else {
                System.out.println("Transaction not found ...");
            }

            // Data remains in the database
            System.out.println("\nData has been saved. Check your database for the saved user and transactions.");
        };
    }
}
