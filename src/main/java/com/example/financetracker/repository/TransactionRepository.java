package com.example.financetracker.repository;

import com.example.financetracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser_Id(Long userId);
    List<Transaction> findByUser_IdAndCategory(Long userId, String category);
}
