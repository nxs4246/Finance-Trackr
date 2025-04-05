package com.example.financetrackr.service;

import com.example.financetrackr.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> findAll();
    Transaction findById(int id);
    Transaction save(Transaction theTransaction);
    void deleteById(int id);

}
