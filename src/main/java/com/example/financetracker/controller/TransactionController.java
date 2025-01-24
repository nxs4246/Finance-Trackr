package com.example.financetracker.controller;

import com.example.financetracker.dto.TransactionRequest;
import com.example.financetracker.dto.TransactionResponse;
import com.example.financetracker.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Add a transaction
    @PostMapping("/{userId}")
    public ResponseEntity<TransactionResponse> addTransaction(
            @PathVariable Long userId,
            @RequestBody TransactionRequest transactionRequest) {
        TransactionResponse createdTransaction = transactionService.addTransaction(userId, transactionRequest);
        return ResponseEntity.ok(createdTransaction);
    }

    // Update a transaction
    @PutMapping("/{userId}/{transactionId}")
    public ResponseEntity<TransactionResponse> updateTransaction(
            @PathVariable Long userId,
            @PathVariable Long transactionId,
            @RequestBody TransactionRequest transactionRequest) {
        TransactionResponse updatedTransaction = transactionService.updateTransaction(userId, transactionId, transactionRequest);
        return ResponseEntity.ok(updatedTransaction);
    }

    // Delete a transaction
    @DeleteMapping("/{userId}/{transactionId}")
    public ResponseEntity<String> deleteTransaction(
            @PathVariable Long userId,
            @PathVariable Long transactionId) {
        transactionService.deleteTransaction(userId, transactionId);
        return ResponseEntity.ok("Transaction deleted successfully.");
    }

    // Get all transactions for a user
    @GetMapping("/{userId}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsForUser(@PathVariable Long userId) {
        List<TransactionResponse> transactions = transactionService.getTransactionsForUser(userId);
        return ResponseEntity.ok(transactions);
    }

    // Get transactions by category for a user
    @GetMapping("/{userId}/category/{category}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByCategory(
            @PathVariable Long userId,
            @PathVariable String category) {
        List<TransactionResponse> transactions = transactionService.getTransactionsByCategory(userId, category);
        return ResponseEntity.ok(transactions);
    }

    // Get transactions by type for a user
    @GetMapping("/{userId}/type/{type}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByType(
            @PathVariable Long userId,
            @PathVariable String type) {
        List<TransactionResponse> transactions = transactionService.getTransactionsByType(userId, type);
        return ResponseEntity.ok(transactions);
    }
}
