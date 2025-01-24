package com.example.financetracker.service;

import com.example.financetracker.dto.TransactionRequest;
import com.example.financetracker.dto.TransactionResponse;
import com.example.financetracker.model.Transaction;
import com.example.financetracker.model.User;
import com.example.financetracker.repository.TransactionRepository;
import com.example.financetracker.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    // Add a transaction
    public TransactionResponse addTransaction(Long userId, TransactionRequest transactionRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Transaction transaction = new Transaction();
        transaction.setType(transactionRequest.getType());
        transaction.setCategory(transactionRequest.getCategory());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setDate(transactionRequest.getDate());
        transaction.setDescription(transactionRequest.getDescription());
        transaction.setUser(user);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return mapToResponse(savedTransaction);
    }

    // Update a transaction
    public TransactionResponse updateTransaction(Long userId, Long transactionId, TransactionRequest transactionRequest) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with id: " + transactionId));

        // Ensure the transaction belongs to the user
        if (!transaction.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Unauthorized to update this transaction.");
        }

        transaction.setType(transactionRequest.getType());
        transaction.setCategory(transactionRequest.getCategory());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setDate(transactionRequest.getDate());
        transaction.setDescription(transactionRequest.getDescription());

        Transaction updatedTransaction = transactionRepository.save(transaction);
        return mapToResponse(updatedTransaction);
    }

    // Delete a transaction
    public void deleteTransaction(Long userId, Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with id: " + transactionId));

        // Ensure the transaction belongs to the user
        if (!transaction.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Unauthorized to delete this transaction.");
        }

        transactionRepository.delete(transaction);
    }

    // Get all transactions for a user
    public List<TransactionResponse> getTransactionsForUser(Long userId) {
        return transactionRepository.findByUser_Id(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get transactions by category
    public List<TransactionResponse> getTransactionsByCategory(Long userId, String category) {
        return transactionRepository.findByUser_IdAndCategory(userId, category)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get transactions by type (Income/Expense)
    public List<TransactionResponse> getTransactionsByType(Long userId, String type) {
        return transactionRepository.findByUser_IdAndType(userId, type)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Helper method to map Transaction to TransactionResponse
    private TransactionResponse mapToResponse(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setId(transaction.getId());
        response.setType(transaction.getType());
        response.setCategory(transaction.getCategory());
        response.setAmount(transaction.getAmount());
        response.setDate(transaction.getDate());
        response.setDescription(transaction.getDescription());
        return response;
    }
}
