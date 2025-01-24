package com.example.financetracker.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponse {

    private Long id;
    private String type;
    private String category;
    private Double amount;
    private LocalDate date;
    private String description;

}
