package com.example.financetrackr.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="type")
    private String type;

    @Column(name="amount")
    private Double amount;

    @Column(name="date")
    private LocalDate date;

    @Column(name="description")
    private String description;

    public Transaction() {

    }

    public Transaction(String type, Double amount, LocalDate date, String description) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id =" + id +
                ", type ='" + type + '\'' +
                ", amount ='" + amount + '\'' +
                ", date ='" + date + '\'' +
                ", description ='" + description + '\'' +
                '}';
    }
}
