package com.example.bank.controller;

import com.example.bank.dto.CardDto;
import com.example.bank.service.CardServiceInterface;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    // Внедряем ИНТЕРФЕЙС, а не класс (это исправление пункта №12)
    private final CardServiceInterface cardService;

    public CardController(CardServiceInterface cardService) {
        this.cardService = cardService;
    }

    // 1. Просмотр карт (теперь возвращает CardDto, исправляем пункт №4)
    @GetMapping("/{owner}")
    public List<CardDto> getCards(@PathVariable String owner) {
        return cardService.getCardsByOwner(owner);
    }

    // 2. Перевод денег
    @PostMapping("/transfer")
    public String transfer(@RequestParam String from,
                           @RequestParam String to,
                           @RequestParam BigDecimal amount) {
        return cardService.transferMoney(from, to, amount);
    }

    // 3. Блокировка карты
    @PatchMapping("/block/{number}")
    public String block(@PathVariable String number) {
        return cardService.blockCard(number);
    }

    // 4. Создание новой карты (принимает и возвращает CardDto, исправляем пункт №4)
    @PostMapping("/create")
    public CardDto createCard(@Valid @RequestBody CardDto cardDto) {
        return cardService.saveCard(cardDto);
    }
}
