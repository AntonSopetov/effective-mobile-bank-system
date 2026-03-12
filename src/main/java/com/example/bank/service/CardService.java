package com.example.bank.service;

import com.example.bank.entity.Card;
import com.example.bank.repository.CardRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
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
            card.setCardNumber("**** **** **** " + num.substring(num.length() - 4));
        });
        return cards;
    }

    public String transferMoney(String fromNumber, String toNumber, BigDecimal amount) {
        Card fromCard = cardRepository.findAll().stream()
                .filter(c -> c.getCardNumber().equals(fromNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Карта отправителя не найдена"));

        Card toCard = cardRepository.findAll().stream()
                .filter(c -> c.getCardNumber().equals(toNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Карта получателя не найдена"));

        if (fromCard.getBalance().compareTo(amount) < 0) {
            return "Недостаточно средств!";
        }

        fromCard.setBalance(fromCard.getBalance().subtract(amount));
        toCard.setBalance(toCard.getBalance().add(amount));

        cardRepository.save(fromCard);
        cardRepository.save(toCard);

        return "Перевод выполнен успешно!";
    }

    public String blockCard(String cardNumber) {
        Card card = cardRepository.findAll().stream()
                .filter(c -> c.getCardNumber().equals(cardNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Карта не найдена"));

        card.setStatus("BLOCKED");
        cardRepository.save(card);
        return "Карта " + cardNumber + " заблокирована!";
    }

    public Card createCard(Card card) {
        if (card.getBalance() == null) {
            card.setBalance(java.math.BigDecimal.ZERO);
        }
        card.setStatus("ACTIVE");
        return cardRepository.save(card);
    }
}
