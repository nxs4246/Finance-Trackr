package com.example.financetrackr.service;

import com.example.financetrackr.dao.TransactionRepository;
import com.example.financetrackr.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll(Sort.by(Sort.Order.desc("date")));
    }

    @Override
    public Transaction findById(int id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Did not find transaction with id - " + id));
    }

    @Override
    public Transaction save(Transaction theTransaction) {
        return transactionRepository.save(theTransaction);
    }

    @Override
    public void deleteById(int id) {
        transactionRepository.deleteById(id);
    }
}
