package com.example.bank.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CardDto {
    private String cardNumber;
    private String owner;
    private String status;
    private BigDecimal balance;
}