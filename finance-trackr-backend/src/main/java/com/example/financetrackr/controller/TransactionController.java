package com.example.financetrackr.controller;

import com.example.financetrackr.entity.Transaction;
import com.example.financetrackr.service.OpenRouterService;
import com.example.financetrackr.service.OpenRouterServiceImpl;
import com.example.financetrackr.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final OpenRouterService openRouterService;

    @Autowired
    public TransactionController(TransactionService transactionService, OpenRouterService openRouterService) {
        this.transactionService = transactionService;
        this.openRouterService = openRouterService;
    }

    // GET all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.findAll();
    }

    // GET one transaction by ID
    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable int id) {
        return transactionService.findById(id);
    }

    // CREATE or UPDATE transaction
    @PostMapping
    public Transaction saveTransaction(@RequestBody Transaction theTransaction) {
        return transactionService.save(theTransaction);
    }

    // DELETE transaction
    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable int id) {
        transactionService.deleteById(id);
        return "Deleted transaction id - " + id;
    }

    // POST to chat with AI using transaction context
    @PostMapping("/chat")
    public Map<String, String> askFinancialAdvisor(@RequestBody Map<String, String> payload) {
        String userPrompt = payload.get("userPrompt");
        List<Transaction> theTransactions = transactionService.findAll();

        StringBuilder contextBuilder = new StringBuilder();
        for (Transaction t : theTransactions) {
            contextBuilder.append("Type: ").append(t.getType())
                    .append(", Amount: ").append(t.getAmount())
                    .append(", Date: ").append(t.getDate())
                    .append(", Description: ").append(t.getDescription())
                    .append("\n");
        }

        String fullPrompt = "Here are my recent transactions:\n" + contextBuilder +
                "\nNow answer this: " + userPrompt;

        String aiResponse = openRouterService.getChatResponse(fullPrompt);

        return Map.of("aiResponse", aiResponse);
    }
}

/* This portion of the code below is for Thymeleaf not React.

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;
    private OpenRouterService openRouterService;

    @Autowired
    public TransactionController(TransactionService transactionService, OpenRouterService openRouterService) {
        this.transactionService = transactionService;
        this.openRouterService = openRouterService;
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

    @PostMapping("/chat")
    public String askFinancialAdvisor(@RequestParam("userPrompt") String userPrompt, Model model) {
        List<Transaction> theTransactions = transactionService.findAll();

        // Convert transactions to readable format for AI
        StringBuilder contextBuilder = new StringBuilder();
        for (Transaction t : theTransactions) {
            contextBuilder.append("Type: ").append(t.getType())
                    .append(", Amount: ").append(t.getAmount())
                    .append(", Date: ").append(t.getDate())
                    .append(", Description: ").append(t.getDescription())
                    .append("\n");
        }

        String fullPrompt = "Here are my recent transactions:\n" + contextBuilder +
                "\nNow answer this: " + userPrompt;

        // Call AI with user prompt
        String aiResponse = openRouterService.getChatResponse(fullPrompt);

        model.addAttribute("transactions", theTransactions);
        model.addAttribute("aiResponse", aiResponse);

        return "transactions/list-transactions";
    }
}
*/