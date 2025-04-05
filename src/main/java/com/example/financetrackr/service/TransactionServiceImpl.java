package com.example.financetrackr.service;

import com.example.financetrackr.dao.TransactionRepository;
import com.example.financetrackr.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction findById(int id) {
        return null;
    }

    @Override
    public Transaction save(Transaction theTransaction) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
