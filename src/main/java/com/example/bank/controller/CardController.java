package com.example.bank.controller;

import com.example.bank.entity.Card;
import com.example.bank.service.CardService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{owner}")
    public List<Card> getCards(@PathVariable String owner) {
        return cardService.getCardsByOwner(owner);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam String from,
                           @RequestParam String to,
                           @RequestParam BigDecimal amount) {
        return cardService.transferMoney(from, to, amount);
    }

    @PatchMapping("/block/{number}")
    public String block(@PathVariable String number) {
        return cardService.blockCard(number);
    }

    @PostMapping("/create")
    public Card createCard(@Valid @RequestBody Card card) {
        return cardService.saveCard(card);
    }
}
