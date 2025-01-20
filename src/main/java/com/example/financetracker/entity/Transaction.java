package com.example.financetracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column
    private String description;

    @Column(nullable = false)
    @Positive
    private double amount;

    @Column(nullable = false)
    private LocalDate date; // Changed from String to LocalDate

    @NotNull
    @Enumerated(EnumType.STRING) // Ensures type is stored as a string in the database
    @Column(nullable = false)
    private enumTransactionType t_type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    protected Transaction() {
    }

    public Transaction(String description, double amount, LocalDate date, enumTransactionType t_type, User user, Category category) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.t_type = t_type;
        this.user = user;
        this.category = category;
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

    public enumTransactionType getType() {
        return t_type;
    }

    public void setType(enumTransactionType t_type) {
        this.t_type = t_type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", description='" + description +
                ", amount=" + amount +
                ", date=" + date +
                ", type=" + t_type +
                ", user=" + (user != null ? user.getUserId() : "null") +
                ", category=" + (category != null ? category.getCategoryId() : "null") +
                '}';
    }

}
