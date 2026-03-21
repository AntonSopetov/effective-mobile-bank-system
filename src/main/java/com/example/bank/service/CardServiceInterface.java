package com.example.bank.service;

import com.example.bank.dto.CardDto;
import java.math.BigDecimal;
import java.util.List;

public interface CardServiceInterface {
    List<CardDto> getCardsByOwner(String owner);
    String transferMoney(String fromNumber, String toNumber, BigDecimal amount);
    String blockCard(String cardNumber);
    CardDto saveCard(CardDto cardDto);
}
