package com.example.financetracker.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {

    @NotNull
    private String type; // Income or Expense

    @NotNull
    private String category;

    @Positive
    @NotNull
    private Double amount;

    @NotNull
    private LocalDate date;

    private String description;
}
