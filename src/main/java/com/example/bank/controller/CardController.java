package com.example.bank.controller;

import com.example.bank.entity.Card;
import com.example.bank.service.CardService;
import org.springframework.web.bind.annotation.*;
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
}
