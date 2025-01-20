package com.example.financetracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private enumCategoryType c_type;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    protected Category() {
    }

    public Category(enumCategoryType c_type) {
        this.c_type = c_type;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public enumCategoryType getC_type() {
        return c_type;
    }

    public void setC_type(enumCategoryType c_type) {
        this.c_type = c_type;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", c_type=" + c_type +
                '}';
    }

}
