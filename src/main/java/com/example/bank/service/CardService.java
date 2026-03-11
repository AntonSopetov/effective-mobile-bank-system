package com.example.bank.service;

import com.example.bank.entity.Card;
import com.example.bank.repository.CardRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getCardsByOwner(String owner) {
        List<Card> cards = cardRepository.findByOwner(owner);

        cards.forEach(card -> {
            String num = card.getCardNumber();
            if (num != null && num.length() >= 4) {
                card.setCardNumber("**** **** **** " + num.substring(num.length() - 4));
            }
        });

        return cards;
    }
}
