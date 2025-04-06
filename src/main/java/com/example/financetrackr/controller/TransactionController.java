package com.example.financetrackr.controller;

import com.example.financetrackr.entity.Transaction;
import com.example.financetrackr.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/list")
    public String listTransactions(Model theModel) {

        return transactionService.findAll().toString();
        //List<Transaction> theTransactions = transactionService.findAll();

        // add to the spring model
        //theModel.addAttribute("transactions", theTransactions);

        // this file is under templates/transactions/list-transactions.html
        //return "transactions/list-transactions";
    }
}
