package com.example.financetrackr.service;

import com.example.financetrackr.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    List<Transaction> findAll();
    Transaction findById(int id);
    Transaction save(Transaction theTransaction);
    void deleteById(int id);


}
