package com.example.financetracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column
    private String description;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDate date; // Changed from String to LocalDate

    @NotNull
    @Enumerated(EnumType.STRING) // Ensures type is stored as a string in the database
    @Column(nullable = false)
    private TransactionType type;


    protected Transaction() {

    }

    public Transaction(String description, double amount, LocalDate date, TransactionType type) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

}
