package com.example.financetrackr.controller;

import com.example.financetrackr.entity.Transaction;
import com.example.financetrackr.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/list")
    public String listTransactions(Model theModel) {

        List<Transaction> theTransactions = transactionService.findAll();

        // add to the spring model
        theModel.addAttribute("transactions", theTransactions);

        // this file is under templates/transactions/list-transactions.html
        return "transactions/list-transactions";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Transaction theTransaction = new Transaction();

        // add to the spring model
        theModel.addAttribute("transaction", theTransaction);

        return "transactions/transaction-form";
    }

    @PostMapping("/save")
    public String saveTransaction(@ModelAttribute("transaction") Transaction theTransaction) {
        // save the transaction
        transactionService.save(theTransaction);
        // use a redirect to prevent duplicate submission
        return "redirect:/transactions/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("transactionId") int theId, Model theModel) {

        // get the transaction from the service
        Transaction theTransaction = transactionService.findById(theId);

        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("transaction", theTransaction);

        // send over to our form
        return "transactions/transaction-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("transactionId") int theId) {

        transactionService.deleteById(theId);
        return "redirect:/transactions/list";
    }
}
